package Coursera.EightPuzzle;
import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private boolean solvable;
    private Stack<Board> solutions;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }
        solve(initial);
    }

    private void solve(Board initial) {
        Node goal = new Node(initial, null);
        MinPQ<Node> queue = new MinPQ<>();
        solvable = goal.isGoal();
        queue.insert(goal);

        Node twin = new Node(initial.twin(), null);
        MinPQ<Node> twinQueue = new MinPQ<>();
        twinQueue.insert(twin);

        while (!solvable && !twin.isGoal()) {
            goal = queue.delMin();
            Iterable<Node> neighbor = goal.neighbor();
            for (Node n : neighbor) {
                queue.insert(n);
            }
            solvable = goal.isGoal();

            twin = twinQueue.delMin();
            Iterable<Node> twinNeighbor = twin.neighbor();
            for (Node n : twinNeighbor) {
                twinQueue.insert(n);
            }
        }

        if (solvable) {
            solutions = goal.getAllStep();
        }
    }
    
    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (solvable) {
            return solutions.size() - 1;
        }
        return -1;
    }
        

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (solvable) {
            return solutions;
        }
        return null;
    }

    private class Node implements Comparable<Node> {
        private final Board board;
        private final Node previous;
        private final int step;
        private final int priority;

        public Node(Board board, Node previous) {
            this.board = board;
            this.previous = previous;
            if (previous != null) {
                this.step = previous.step + 1;
            } else {
                step = 0;
            }
            this.priority = board.manhattan() + step; 
        }

        public Iterable<Node> neighbor() {
            ArrayList<Node> neighbor = new ArrayList<>();
            Iterable<Board> neighborBoard = board.neighbors();
            for (Board b : neighborBoard) {
                if (previous != null) { 
                    if (!previous.board.equals(b)) {
                        Node newNode = new Node(b, this);
                        neighbor.add(newNode);
                    }
                } else {
                    Node newNode = new Node(b, this);
                    neighbor.add(newNode);
                }
            }
            return neighbor;
        }

        public Stack<Board> getAllStep() {
            Stack<Board> solution = new Stack<>();
            Node query = this;
            while (query != null) {
                solution.push(query.board);
                query = query.previous;
            }
            return solution;
        }

        public boolean isGoal() {
            return board.isGoal();
        }

        @Override
        public int compareTo(Solver.Node o) {
            return Integer.compare(this.priority, o.priority);
        }
    }


    // test client (see below) 
    public static void main(String[] args) {

        // create initial board from file
        In in = new In("In.txt");
    int n = in.readInt();
    int[][] tiles = new int[n][n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            tiles[i][j] = in.readInt();
    Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}