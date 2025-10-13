import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class blockcrusher {

    static int[][] grid;
    static List<String> input;
    static int n,l;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String line;

        while (!(line=bufferedReader.readLine()).equals("0 0")){
            //Read inputs
            String[] split = line.split(" ");
            n = Integer.parseInt(split[0]);
            l = Integer.parseInt(split[1]);
            
            int minValue = 1;
            
            input = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                line = bufferedReader.readLine();
                input.add(line);
            }
            
            
            //Create grid of nodes with start and endNode
            node startNode = new node(0, minValue, n+1,-1,-1);
            node endNode = new node(0, minValue, 0,-1,-1);

            node[][] gridNode = createNodeGrid(input, minValue, startNode,endNode);


            List<Integer[]> path = aStarPathfinding(startNode, endNode);


            List<String> output = new ArrayList<>(input);
            for (Integer[] p:path){
                StringBuilder l = new StringBuilder(output.get(p[1]));
                l.setCharAt(p[0],' ');
                output.set(p[1],l.toString());
            }

            for (String l:output){
                System.out.println(l);
            }
            System.out.println();
        }
    }

    private static List<Integer[]> aStarPathfinding(node startNode, node endNode) {
        List<node> open = new ArrayList<>();
        open.add(startNode);
        startNode.closed = true;
        while (!open.isEmpty()){
            Collections.sort(open);

            node current = open.get(0);
            open.remove(0);
            current.closed = true;

            if (current.ID == endNode.ID)  //path is found
                break;

            for(node node: current.adj){
                if(node.closed) continue;

                int g_cost = current.g + node.weight;
                if(g_cost < node.g || !open.contains(node)){
                    node.setG(g_cost);
                    node.setParent(current);

                    if(!open.contains(node)){
                        open.add(node);
                    }
                }

            }
        }

        node btNode = endNode;
        List<Integer[]> path = new ArrayList<>();

        while (true){
            btNode = btNode.parent;
            if(btNode == startNode) break;
            path.add(new Integer[]{btNode.gridX, btNode.gridY});
        }

        Collections.reverse(path);
        return path;
    }

    private static node[][] createNodeGrid(List<String> input, int minValue, node startNode, node endNode) {
        grid = new int[n][l];
        for (int i = 0; i < n; i++) {
            String line = input.get(i);
            for (int j = 0; j < l; j++) {
                grid[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }

        node[][] gridNode = new node[n][l];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < l; j++) {
                gridNode[i][j] = new node(grid[i][j], minValue,n-i,j,i);
            }
        }


        int[] xx = new int[]{-1,0,1,1,1,0,-1,-1}; //all directions x
        int[] yy = new int[]{-1,-1,-1,0,1,1,1,0}; //all directions y
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < l; j++) {
                if(i==0){
                    startNode.addVertex(gridNode[i][j]);
                    gridNode[i][j].addVertex(startNode);
                }
                if(i==n-1){
                    endNode.addVertex(gridNode[i][j]);
                    gridNode[i][j].addVertex(endNode);
                }

                for (int k = 0; k < xx.length; k++) {
                    int x = xx[k] + i;
                    int y = yy[k] + j;
                    if (x < 0 || x >= n || y < 0 || y >= l) continue;

                    gridNode[x][y].addVertex(gridNode[i][j]);
                    gridNode[i][j].addVertex(gridNode[x][y]);
                }

            }
        }

        return gridNode;
    }


}

class node implements Comparable<node> {
    @Override
    public String toString() {
        return "node{" +
                "f=" + f +
                ", g=" + g +
                ", h=" + h +
                ", ID=" + ID +
                '}';
    }


    boolean closed;
    int f,g,h;
    final int gridX,gridY;
    node parent;
    final int ID;
    static int ID_unifier = 0;
    final int weight;
    final List<node> adj;


    public void addVertex(node n) {
        if(!adj.contains(n)) this.adj.add(n);
    }

    public void setG(int g) {
        this.g = g;
        this.f = this.g + this.h;
    }

    public void setParent(node parent) {
        this.parent = parent;
    }

    public node(int weight, int minValue, int distTarget, int gridX, int gridY) {
        ID = ID_unifier++;
        this.weight = weight;
        adj = new ArrayList<>();
        closed = false;
        this.h = minValue*distTarget;
        this.g = 0;
        this.f = this.h + this.g;
        this.gridX = gridX;
        this.gridY = gridY;
    }



    @Override
    public int compareTo(node o) {
        long i1 = 48611L * this.f + (long) this.h;
        long i2 = 48611L * o.f + (long) o.h;
        return (int) (i1-i2);
    }
}
