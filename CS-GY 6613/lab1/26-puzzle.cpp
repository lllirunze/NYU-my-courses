#include<iostream>
#include<fstream>
#include<iomanip>
#include<vector>
#include<map>
#include<queue>
#include<cmath>

using namespace std;

// The structure of state, including the state, f(n), etc.
typedef struct node {
    vector<vector<vector<int>>> state;
    string action;              // store the action along the path
    int depth;                  // g(n)
    int manhattan_Distance;     // h(n)
    int f;                      // f(n) = g(n) + h(n)
    vector<int> f_sequence;     // store the f(n) along the path
    vector<int> blank_Position; // the position of blank position: x, y, z   
} node;

struct cmp {
    bool operator()(node* a, node* b) {
        return a->f > b->f;
    }
};


/*
 * We use a map in C++ programming language as hash table, 
 * which is used to determine whether the state is reached.
 * 
 * reached[state] = f(state)
 */ 
map<vector<vector<vector<int>>>, int> reached;
/*
 * Initialize the initial state and goal state(3*3*3)
 */
vector<vector<vector<int>>> initial_state(3, vector<vector<int>>(3, vector<int>(3, 0)));
vector<vector<vector<int>>> goal_state(3, vector<vector<int>>(3, vector<int>(3, 0)));
/*
 * Initialize the index of every value in goal state(27*3)
 * index_of_goal_state[value] = {x, y, z}
 */
vector<vector<int>> index_of_goal_state(27, vector<int>(3, 0));
priority_queue<node*, vector<node*>, cmp> frontier;

/*
 * Calculate the manhattan distance = abs(delta(x)) + abs(delta(y)) + abs(delta(z))
 */
int calculate_Manhattan_Distance(vector<vector<vector<int>>> state) {
    int manhattan_Distance = 0;
    for (int x=0; x<3; x++) {
        for (int y=0; y<3; y++) {
            for (int z=0; z<3; z++) {
                if (state[x][y][z] == 0) continue;
                manhattan_Distance += abs(x - index_of_goal_state[state[x][y][z]][0]);
                manhattan_Distance += abs(y - index_of_goal_state[state[x][y][z]][1]);
                manhattan_Distance += abs(z - index_of_goal_state[state[x][y][z]][2]);
            }
        }
    }
    return manhattan_Distance;
}

/*
 * Find the index of blank position {x, y, z}
 */
vector<int> find_Blank_Position(vector<vector<vector<int>>> state) {
    vector<int> blank_position(3, 0);
    for (int x=0; x<3; x++) {
        for (int y=0; y<3; y++) {
            for (int z=0; z<3; z++) {
                if (state[x][y][z] == 0) {
                    blank_position[0] = x;
                    blank_position[1] = y;
                    blank_position[2] = z;
                    return blank_position;
                }
            }
        }
    }
    return blank_position;
}

/*
 * Check whether it is goal state
 */
bool check_Goal_State(vector<vector<vector<int>>> state) {
    for (int x=0; x<3; x++) {
        for (int y=0; y<3; y++) {
            for (int z=0; z<3; z++) {
                if (state[x][y][z] != goal_state[x][y][z]) {
                    return false;
                }
            }
        }
    }
    return true;
}

/*
 * Expand the node and get node's children
 */
vector<node*> expand_State(node* front_node) {
    vector<node*> children;
    int blank_x = front_node->blank_Position[0];
    int blank_y = front_node->blank_Position[1];
    int blank_z = front_node->blank_Position[2];

    /*
     * Get next valid state using a action in {E, W, N, S, U, D}
     */

    /*
     * Action East
     */
    if (blank_x < 2) {
        node* child_East = new node();
        /*
         * Move blank position to the east
         */
        child_East->state.assign(front_node->state.begin(), front_node->state.end());
        swap(child_East->state[blank_x][blank_y][blank_z], child_East->state[blank_x + 1][blank_y][blank_z]);
        /*
         * Get the new action
         */
        child_East->action = front_node->action;
        child_East->action += "E";
        /*
         * Add one to the depth level of child state
         */
        child_East->depth = front_node->depth + 1;
        /*
         * Get the new manhattan distance
         */
        child_East->manhattan_Distance = calculate_Manhattan_Distance(child_East->state);
        /*
         * f(n) = g(n) + h(n)
         */
        child_East->f = child_East->depth + child_East->manhattan_Distance;
        /*
         * Add the new f(n) to the sequence
         */
        child_East->f_sequence.assign(front_node->f_sequence.begin(), front_node->f_sequence.end());
        child_East->f_sequence.push_back(child_East->f);
        /*
         * Get the new index of blank position
         */
        child_East->blank_Position = find_Blank_Position(child_East->state);

        children.push_back(child_East);
    }

    /*
     * Action West
     */
    if (blank_x > 0) {
        node* child_West = new node();

        child_West->state.assign(front_node->state.begin(), front_node->state.end());
        swap(child_West->state[blank_x][blank_y][blank_z], child_West->state[blank_x - 1][blank_y][blank_z]);
    
        child_West->action = front_node->action;
        child_West->action += "W";

        child_West->depth = front_node->depth + 1;

        child_West->manhattan_Distance = calculate_Manhattan_Distance(child_West->state);

        child_West->f = child_West->depth + child_West->manhattan_Distance;

        child_West->f_sequence.assign(front_node->f_sequence.begin(), front_node->f_sequence.end());
        child_West->f_sequence.push_back(child_West->f);

        child_West->blank_Position = find_Blank_Position(child_West->state);
    
        children.push_back(child_West);
    }

    /*
     * Action North
     */
    if (blank_y < 2) {
        node* child_North = new node();

        child_North->state.assign(front_node->state.begin(), front_node->state.end());
        swap(child_North->state[blank_x][blank_y][blank_z], child_North->state[blank_x][blank_y + 1][blank_z]);

        child_North->action = front_node->action;
        child_North->action += "N";

        child_North->depth = front_node->depth + 1;

        child_North->manhattan_Distance = calculate_Manhattan_Distance(child_North->state);

        child_North->f = child_North->depth + child_North->manhattan_Distance;

        child_North->f_sequence.assign(front_node->f_sequence.begin(), front_node->f_sequence.end());
        child_North->f_sequence.push_back(child_North->f);

        child_North->blank_Position = find_Blank_Position(child_North->state);

        children.push_back(child_North);
    }

    /*
     * Action South
     */
    if (blank_y > 0) {
        node* child_South = new node();

        child_South->state.assign(front_node->state.begin(), front_node->state.end());
        swap(child_South->state[blank_x][blank_y][blank_z], child_South->state[blank_x][blank_y - 1][blank_z]);

        child_South->action = front_node->action;
        child_South->action += "S";
        
        child_South->depth = front_node->depth + 1;

        child_South->manhattan_Distance = calculate_Manhattan_Distance(child_South->state);

        child_South->f = child_South->depth + child_South->manhattan_Distance;

        child_South->f_sequence.assign(front_node->f_sequence.begin(), front_node->f_sequence.end());
        child_South->f_sequence.push_back(child_South->f);

        child_South->blank_Position = find_Blank_Position(child_South->state);

        children.push_back(child_South);
    }

    /*
     * Action Up
     */
    if (blank_z < 2) {
        node* child_Up = new node();

        child_Up->state.assign(front_node->state.begin(), front_node->state.end());
        swap(child_Up->state[blank_x][blank_y][blank_z], child_Up->state[blank_x][blank_y][blank_z + 1]);

        child_Up->action = front_node->action;
        child_Up->action += "U";

        child_Up->depth = front_node->depth + 1;

        child_Up->manhattan_Distance = calculate_Manhattan_Distance(child_Up->state);

        child_Up->f = child_Up->depth + child_Up->manhattan_Distance;

        child_Up->f_sequence.assign(front_node->f_sequence.begin(), front_node->f_sequence.end());
        child_Up->f_sequence.push_back(child_Up->f);

        child_Up->blank_Position = find_Blank_Position(child_Up->state);

        children.push_back(child_Up);
    }

    /*
     * Action Down
     */
    if (blank_z > 0) {
        node* child_Down = new node();

        child_Down->state.assign(front_node->state.begin(), front_node->state.end());
        swap(child_Down->state[blank_x][blank_y][blank_z], child_Down->state[blank_x][blank_y][blank_z - 1]);

        child_Down->action = front_node->action;
        child_Down->action += "D";

        child_Down->depth = front_node->depth + 1;

        child_Down->manhattan_Distance = calculate_Manhattan_Distance(child_Down->state);

        child_Down->f = child_Down->depth + child_Down->manhattan_Distance;

        child_Down->f_sequence.assign(front_node->f_sequence.begin(), front_node->f_sequence.end());
        child_Down->f_sequence.push_back(child_Down->f);

        child_Down->blank_Position = find_Blank_Position(child_Down->state);

        children.push_back(child_Down);
    }

    return children;
}

int main() {

    // Read the file
    ifstream infile("input.txt", ios::in);
    if (!infile.is_open()) {
        cout << "open error!" << endl;
        return 0;
    }

    /*
     * Store the initial state and goal state into the array
     *
     */
    for (int z=2; z>=0; z--) {
        for (int y=2; y>=0; y--) {
            for (int x=0; x<3; x++) {
                if (infile.eof()) {
                    cout << "read error" << endl;
                    return 0;
                }
                infile >> initial_state[x][y][z];
            }
        }
    }
    for (int z=2; z>=0; z--) {
        for (int y=2; y>=0; y--) {
            for (int x=0; x<3; x++) {
                if (infile.eof()) {
                    cout << "read error" << endl;
                    return 0;
                }
                infile >> goal_state[x][y][z];
            }
        }
    }

    /*
     * Store the index of every value in goal state
     */
    for (int x=0; x<3; x++) {
        for (int y=0; y<3; y++) {
            for (int z=0; z<3; z++) {
                index_of_goal_state[goal_state[x][y][z]][0] = x;
                index_of_goal_state[goal_state[x][y][z]][1] = y;
                index_of_goal_state[goal_state[x][y][z]][2] = z;
            }
        }
    }

    node* start_node = new node();
    start_node->state.assign(initial_state.begin(), initial_state.end());
    start_node->action = "";
    start_node->depth = 0;
    start_node->manhattan_Distance = calculate_Manhattan_Distance(start_node->state);
    start_node->f = start_node->depth + start_node->manhattan_Distance;
    start_node->f_sequence.push_back(start_node->f);
    start_node->blank_Position = find_Blank_Position(start_node->state);


    /*
     * Firstly, we need to push the start node into priority queue.
     */
    frontier.push(start_node);
    reached.emplace(start_node->state, start_node->f);

    node* front_node;
    bool find_Goal = false;

    while (!frontier.empty()) {
        /*
         * Get the head of the frontier
         */

        front_node = frontier.top();
        frontier.pop();

        
        /*
         * Determine whether the node is goal state
         */
        if (check_Goal_State(front_node->state)) {
            /*
             * We have successfully found the result.
             */
            find_Goal = true;
            break;
        }


        /*
         * Expand the node, i.e. get the next step of the state
         */
        vector<node*> children_states = expand_State(front_node);
    

        for (auto child_state : children_states) {


            /*
             * Check whether the child state isn't in 'reached' table
             * 
             */
            if (reached.find(child_state->state) != reached.end()) continue;
            else {
                /*
                 * Add the child state to 'reached' table and push it into frontier
                 */
                reached.emplace(child_state->state, child_state->f);
                frontier.push(child_state);
            }
        }
    }

    if (find_Goal == false) {
        cout << "We can't find the optimal path from initial state to goal state." << endl;
        return 0;
    }

    /*
     * Write the initial state and goal state in the output file.
     * Line 1-11:  initial state
     * Line 13-23: goal state
     * 
     */ 
    ofstream outfile;
    outfile.open("output.txt", ios::out);
    for (int z=2; z>=0; z--) {
        for (int y=2; y>=0; y--) {
            for (int x=0; x<3; x++) {
                outfile << initial_state[x][y][z] << " ";
            }
            outfile << endl;
        }
        outfile << endl;
    }
    for (int z=2; z>=0; z--) {
        for (int y=2; y>=0; y--) {
            for (int x=0; x<3; x++) {
                outfile << goal_state[x][y][z] << " ";
            }
            outfile << endl;
        }
        outfile << endl;
    }

    /*
     * We output the last 4 line.
     *       Line 25: the depth level d of the shallowest goal node as found 
     *                by the A* algorithm (assume the root node is at level 0).
     *       Line 26: the total number of nodes N generated in my tree 
     *                (including the root node).
     *       Line 27: the solution, i.e. a sequence of actions from root node 
     *                to goal node represented by A's. The A's are separated 
     *                by blank spaces. Each A is a character from the set 
     *                {E, W, N, S, U, D}, representing the movements of the 
     *                blank position(0).
     *       Line 28: f(n) value of the nodes along the solution path, from 
     *                the root node to the goal node, separated by blank spaces.
     */
    outfile << front_node->depth << endl;
    outfile << reached.size() << endl;
    for (int i=0; i < front_node->action.length(); i++) {
        outfile << front_node->action[i] << " ";
    }
    outfile << endl;
    for (int i=0; i < front_node->f_sequence.size(); i++) {
        outfile << front_node->f_sequence[i] << " ";
    }
    outfile << endl;

    // Close the file
    outfile.close();

    return 0;
}