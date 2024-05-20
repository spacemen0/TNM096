#include <iostream>
#include <vector>
#include <queue>
#include <utility>
#include <chrono>
#include <cmath>
#include <unordered_set>

using namespace std;

class Board
{
private:
    vector<vector<int>> state;

public:
    Board(const vector<vector<int>> &initialState) : state(initialState) {}

    vector<vector<int>> getState() const
    {
        return state;
    }

    bool isGoalState(const Board &goalBoard) const
    {
        return state == goalBoard.getState();
    }

    void printBoard() const
    {
        for (const auto &row : state)
        {
            for (int cell : row)
            {
                cout << cell << " ";
            }
            cout << endl;
        }
    }

    pair<int, int> findTilePosition(int tileValue) const
    {
        for (size_t i = 0; i < state.size(); ++i)
        {
            for (size_t j = 0; j < state[i].size(); ++j)
            {
                if (state[i][j] == tileValue)
                {
                    return make_pair(i, j);
                }
            }
        }
        return make_pair(-1, -1);
    }

    void move(char direction)
    {
        pair<int, int> emptyPos = findTilePosition(0);
        int emptyRow = emptyPos.first;
        int emptyCol = emptyPos.second;

        if (direction == 'L' && emptyCol > 0)
        {
            swap(state[emptyRow][emptyCol], state[emptyRow][emptyCol - 1]);
        }
        else if (direction == 'R' && emptyCol < state[0].size() - 1)
        {
            swap(state[emptyRow][emptyCol], state[emptyRow][emptyCol + 1]);
        }
        else if (direction == 'U' && emptyRow > 0)
        {
            swap(state[emptyRow][emptyCol], state[emptyRow - 1][emptyCol]);
        }
        else if (direction == 'D' && emptyRow < state.size() - 1)
        {
            swap(state[emptyRow][emptyCol], state[emptyRow + 1][emptyCol]);
        }
    }
    bool operator==(const Board &other) const
    {
        return state == other.getState();
    }
};

struct BoardHash
{
    size_t operator()(const Board &board) const
    {
        size_t hash = 0;
        for (const auto &row : board.getState())
        {
            for (int value : row)
            {
                hash ^= std::hash<int>()(value) + 0x9e3779b9 + (hash << 6) + (hash >> 2);
            }
        }
        return hash;
    }
};

class Node
{
private:
    Board board;
    int heuristic;
    int pathCost;
    Node *pre;
    char direction;

public:
    Node(const Board &boardState, int h, int cost, Node *parent, char dir) : board(boardState), heuristic(h), pathCost(cost), pre(parent), direction(dir) {}

    Board getBoard() const
    {
        return board;
    }

    int getHeuristic() const
    {
        return heuristic;
    }

    int getPathCost() const
    {
        return pathCost;
    }

    Node *getParent() const
    {
        return pre;
    }

    char getDirection() const
    {
        return direction;
    }

    bool operator<(const Node &other) const
    {
        return heuristic + pathCost > other.heuristic + other.pathCost;
    }
};

class Puzzle
{
private:
    priority_queue<Node> frontierList;
    unordered_set<Board, BoardHash> uniqueSet;
    Board initialState;
    Board goalState;
    int heuristicOption;

    int h1(const Board &currentBoard, const Board &goalBoard) const
    {
        int misplacedTiles = 0;
        auto currentState = currentBoard.getState();
        auto goalState = goalBoard.getState();

        for (size_t i = 0; i < currentState.size(); ++i)
        {
            for (size_t j = 0; j < currentState[i].size(); ++j)
            {
                if (currentState[i][j] != goalState[i][j] && currentState[i][j] != 0)
                {
                    ++misplacedTiles;
                }
            }
        }

        return misplacedTiles;
    }

    int h2(const Board &currentBoard, const Board &goalBoard) const
    {
        int manhattanDistance = 0;
        auto currentState = currentBoard.getState();
        auto goalState = goalBoard.getState();

        for (size_t i = 0; i < currentState.size(); ++i)
        {
            for (size_t j = 0; j < currentState[i].size(); ++j)
            {
                int tileValue = currentState[i][j];
                if (tileValue != 0)
                {
                    auto goalPosition = goalBoard.findTilePosition(tileValue);
                    manhattanDistance += abs(int(i) - goalPosition.first) + abs(int(j) - goalPosition.second);
                }
            }
        }

        return manhattanDistance;
    }

public:
    Puzzle(const Board &initial, const Board &goal, int option) : initialState(initial), goalState(goal), heuristicOption(option)
    {
        int initialHeuristic = calculateHeuristic(initialState);
        Node initialNode(initialState, initialHeuristic, 0, nullptr, ' ');
        addToFrontier(initialNode);
    }

    void addToFrontier(const Node &node)
    {
        frontierList.push(node);
    }

    bool isFrontierEmpty() const
    {
        return frontierList.empty();
    }

    void expand(Node currentNode)
    {
        Board currentBoard = currentNode.getBoard();
        vector<char> directions = {'L', 'R', 'U', 'D'};

        for (char dir : directions)
        {
            Board successorBoard = currentBoard;
            successorBoard.move(dir);
            int successorHeuristic = calculateHeuristic(successorBoard);
            int successorPathCost = currentNode.getPathCost() + 1;
            Node successorNode(successorBoard, successorHeuristic, successorPathCost, &currentNode, dir);
            addToFrontier(successorNode);
        }
    }

    int calculateHeuristic(const Board &board) const
    {
        if (heuristicOption == 1)
        {
            return h1(board, goalState);
        }
        else
        {
            return h2(board, goalState);
        }
    }

    void solve()
    {
        while (!isFrontierEmpty())
        {
            Node currentNode = frontierList.top();
            frontierList.pop();
            if (uniqueSet.insert(currentNode.getBoard()).second)
            {
                if (currentNode.getBoard().isGoalState(goalState))
                {
                    cout << "Solution Found!" << endl;
                    currentNode.getBoard().printBoard();
                    Node *currentPoint = &currentNode;
                    while (currentPoint != nullptr)
                    {
                        cout << "Node " << currentPoint->getPathCost();
                        cout << " direction from parent: " << currentPoint->getDirection() << endl;
                        currentPoint = currentPoint->getParent();
                    }
                    cout << "Path Cost: " << currentNode.getPathCost() << endl;
                    return;
                }
                expand(currentNode);
            }
        }
        cout << "No Solution Found!" << endl;
    }
};

int main(int argc, char *argv[])
{
    if (argc != 2)
    {
        cerr << "Usage: " << argv[0] << " <heuristic_option>" << endl;
        return 1;
    }

    int option = atoi(argv[1]);

    vector<vector<int>> initialBoardState = {
        {8, 6, 7},
        {2, 5, 4},
        {3, 0, 1}};
    vector<vector<int>> goalBoardState = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 0}};

    Board initialBoard(initialBoardState);
    Board goalBoard(goalBoardState);
    Puzzle puzzle(initialBoard, goalBoard, option);

    auto startTime = chrono::high_resolution_clock::now();
    puzzle.solve();
    auto endTime = chrono::high_resolution_clock::now();
    auto duration = chrono::duration_cast<chrono::milliseconds>(endTime - startTime);
    cout << "Time taken to solve the puzzle: " << duration.count() << " milliseconds" << endl;

    return 0;
}
