import java.util.*;

public class Navigation {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int num_cases = Integer.parseInt(sc.nextLine());
        // Run for the number of test cases required
        for (int caseNo = 0; caseNo < num_cases; caseNo++) {
            //Scan size of location data
            int number1 = sc.nextInt();

            //Declaration for a place to store data
            String test, Answer = "";
            String recent[] = new String[3];

            //Call Class GraphMeow
            GraphMeow<String> graph = new GraphMeow<>(number1 + number1);

            //Declare an ArrayList for ShortestRoute method
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>(number1 + number1);

            //ready a place to store an ArrayList inside adj ArrayList
            for (int i = 0; i < (number1 + number1); i++) {
                adj.add(new ArrayList<>());
            }

            sc.nextLine();
            //Data for location and line
            for (int i = 1; i <= number1; i++) {

                test = sc.nextLine();
                recent = test.split(" => ");   //split the string to get Location name

                //add both location to LinkedList in GraphMeow using method
                graph.addLocation(number1, recent[0]);
                graph.addLocation(number1, recent[1]);

                //add Line between d1 & d2 (for LinkedList in GraphMeow) , then add edge between them (to find Shortest Route)
                if (graph.addLine(recent[1], recent[0]) && graph.addLine(recent[0], recent[1]) == true) {
                    int s = graph.hasLocationNo(recent[0]);
                    int d = graph.hasLocationNo(recent[1]);
                    addEdge(adj, s, d);
                }
            }
            int number2 = sc.nextInt();

            int s, d;
            LinkedList<Integer> jalan;

            sc.nextLine();
            for (int i = 1; i <= number2; i++) {

                //for user input
                test = sc.nextLine();
                recent = test.split(" -> ");   //split the string to get Location name

                graph.hasLocation(recent[0]);
                graph.hasLocation(recent[1]);
            /*
            if(graph.hasLocation(recent[0]) == false){
                    System.out.println("This path doesnt start at the starting station!");
                    continue;
                }
            
            if(graph.hasLocation(recent[1]) == false){
                    System.out.println("This path doesnt end at the destination!");
                    continue;
                }
            */
                s = graph.hasLocationNo(recent[0]);
                d = graph.hasLocationNo(recent[1]);

                if (s == -1) {
                    System.out.println("This path doesnt start at the starting station!");
                }
                if (d == -1) {
                    System.out.println("This path doesnt end at the destination!");
                }

                jalan = printShortestDistance(adj, s, d, (number1 + number1));

                if (jalan == null) {
                    System.out.println("There is no train from " + recent[0] + " to " + recent[1]);
                    continue;
                } else {
                    for (int j = jalan.size() - 1; j >= 0; j--) {
                        if (j == 0) {
                            Answer += graph.getAllLocationObjects().get(jalan.get(j));
                            continue;
                        }
                        Answer += graph.getAllLocationObjects().get(jalan.get(j)) + "->";
                    }
                    System.out.println(Answer);
                    Answer = "";
                }
            }
        }
    }
    
    
    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j){
        adj.get(i).add(j);
        adj.get(j).add(i);
    }
    
    // function to print the shortest distance and path
    // between source vertex and destination vertex
    private static LinkedList<Integer> printShortestDistance(ArrayList<ArrayList<Integer>> adj,int s, int dest, int v){
        // predecessor[i] array stores predecessor of
        // i and distance array stores distance of i
        // from s
        int pred[] = new int[v];
        int dist[] = new int[v];
 
        if (BFS(adj, s, dest, v, pred, dist) == false) {
            //System.out.println("This path doesnt start at the starting station!");
            return null;
        }
 
        // LinkedList to store path
        LinkedList<Integer> path = new LinkedList<>();
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }
 
        // Print distance
        //System.out.println("Shortest Path : Next " + dist[dest] + " Location");
        
        return path;
        // Print path
        /*
        System.out.println("Path is ::");
            for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }*/
    }
 
    // a modified version of BFS that stores predecessor
    // of each vertex in array pred
    // and its distance from source in array dist
    private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src,int dest, int v, int pred[], int dist[]){
        // a queue to maintain queue of vertices whose
        // adjacency list is to be scanned as per normal
        // BFS algorithm using LinkedList of Integer type
        LinkedList<Integer> queue = new LinkedList<>();
 
        // boolean array visited[] which stores the
        // information whether ith vertex is reached
        // at least once in the Breadth first search
        boolean visited[] = new boolean[v];
 
        // initially all vertices are unvisited
        // so v[i] for all i is false
        // and as no path is yet constructed
        // dist[i] for all i set to infinity
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }
 
        // now source is first to be visited and
        // distance from source to itself should be 0
        visited[src] = true;
        dist[src] = 0;
        queue.add(src);
 
        // bfs Algorithm
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                if (visited[adj.get(u).get(i)] == false) {
                    visited[adj.get(u).get(i)] = true;
                    dist[adj.get(u).get(i)] = dist[u] + 1;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));
 
                    // stopping condition (when we find
                    // our destination)
                    if (adj.get(u).get(i) == dest)
                        return true;
                }
            }
        }
        return false;
    }
}


class Location<T extends Comparable<T>> extends ArrayList<T> {
        
        T locationInfo;
        int indeg;
        int outdeg;
        int tag;
        int edge;
        Location<T> nextLocation;
        Line<T> firstLine;
        ArrayList<T> neighbour;
        ArrayList<Location> neighbourLocation;
        boolean visited;

        public Location() {
            this.locationInfo = null;
            this.indeg = 0;
            this.outdeg = 0;
            this.tag = 0;
            this.edge = 0;
            this.nextLocation = null;
            this.firstLine = null;
        }

        public Location(int num,T LocationInfo, Location<T> nextLocation) {
            this.locationInfo = LocationInfo;
            this.indeg = 0;
            this.outdeg = 0;
            this.tag = 0;
            this.edge = 0;
            this.nextLocation = nextLocation;
            this.firstLine = null;
            this.neighbour = new ArrayList<>(num);
            this.visited = false;
        }

        void visit() {
            visited = true;
        }

        void unvisit() {
            visited = false;
        }
    }
    
class Line<T extends Comparable<T>> {
        
        Location<T> toLocation;
        Line<T> nextLine;
        boolean visited;
        //ArrayList<T> neighbour;

        public Line() {
            this.toLocation = null;
            this.nextLine = null;
        }

        public Line(Location<T> toLocation, Line<T> nextLine) {
            this.toLocation = toLocation;
            this.nextLine = nextLine;
            //this.neighbour.add(toLocation.locationInfo);
        }
        
        void visit() {
            visited = true;
        }

        void unvisit() {
            visited = false;
        }
    }

class GraphMeow<T extends Comparable<T>> extends ArrayList{
    Location<T> head;
    int size;
    ArrayList<ArrayList<Integer>> adj ;


    //default constructor
    public GraphMeow() {
        head = null;
        size = 0;
    }

    //constructor to prepare ArrayList for Shortest distance
    public GraphMeow(int size) {
        this.adj = new ArrayList<>(size);
        head = null;
        size = 0;
        for (int i = 0; i < size; i++) {
            this.adj.add(new ArrayList<>());
        }
    }

    //method to get the class size
    public int getSize(){
        return this.size;
    }

    //method to check whether location v is exist in the list
    public boolean hasLocation(T v){
        //if list == null
        if(head == null)
            return false;

        //start from head
        Location<T> temp = head; 

        //keep repeating until the end of the list
        while(temp != null){
            if(temp.locationInfo.compareTo(v) == 0)//found location v
                return true;
            temp = temp.nextLocation;//keep moving to the next location if v not found
        }
        return false;//return false as there are no location v
    }

    //method to get the index of location in the array
    public int hasLocationNo(T v){
        //if list == null
        if(head == null)
            return -1;

        //start from head
        Location<T> temp = head; 

        //keep repeating until the end of the list
        while(temp != null){
            if(temp.locationInfo.compareTo(v) == 0)//found location v
                return temp.tag;
            temp = temp.nextLocation;//keep moving to the next location until found v
        }
        return -1;//return false as there are no location v
    }

    //method to add a location v into the list
    public boolean addLocation(int num,T v){
        int no = 0;
        //to check wether location v exist in the list, if not then continue
        if(hasLocation(v) == false){
            Location<T> temp = head;//let the cursor start from head
            Location<T> newlocation = new Location<>(num,v,null);//set location v as new location (*total number of location, *location name, *ArrayList for line)

            //if there is no element in the list, location v will be the head of the list
            if(head == null){
                head = newlocation;
                head.tag = no;
            }

            else{
                Location<T> previous = head;//set another variable for set the nextLocation of the last element in the list
                //we use temp to move the list until the end of the current list
                while(temp != null){
                    no++;
                    previous = temp;
                    temp = temp.nextLocation;
                }
                //cursor will be at the end of the list
                previous.nextLocation = newlocation; //at the end the new element will be added
                previous.nextLocation.tag = no;
            }
            size++;//size of list increase by 1
            return true;
        }
        else    //location already exist
            return false;
    }

    //method to return a list of vertex using ArrayList
    public ArrayList<T> getAllLocationObjects(){
        ArrayList<T> list = new ArrayList<>();
        Location<T> temp = head;//let the cursor start from head of list
        int i=0;

        //the cursor will move to the end of the list
        while (temp != null){
            //add location to ArrayList
            list.add(temp.locationInfo);
            temp = temp.nextLocation;//go to next element in the list;
            i++;
        }
        return list;
    }

    //method to check the edge of locations
    public boolean hasLine(T source, T destination){
        //check wether there are location in the list
        if(head == null) 
            return false;
        //check wether source and destination is exist in the list
        if(!hasLocation(source) || !hasLocation(destination)) 
            return false;

        //set the cursor at the head of the list
        Location<T> sourceLocation = head;

        //run until sourceLocation == source 
        while(sourceLocation != null){
            //source found
            if(sourceLocation.locationInfo.compareTo(source) == 0){
                //set the cursor at the first edge of the location
                Line<T> currentLine = sourceLocation.firstLine;
                //run until last edge of location to check
                while(currentLine != null){
                    //found the destination
                    if(currentLine.toLocation.locationInfo.compareTo(destination) == 0)
                        return true;
                    currentLine = currentLine.nextLine;
                }
            }
            sourceLocation = sourceLocation.nextLocation;
        }
        return false;
    }

    //method to add edge for a existing location
    public boolean addLine(T source, T destination){
        //check wether there are locations in the list
        if(head == null) 
            return false;
        //check wether source and destination is exist in the list
        if(!hasLocation(source) || !hasLocation(destination)) //false sebab antara source and destination tak wujud
            return false;

        //to check if the is existing edge between these locations
        if(hasLine(source,destination) == true)
            return false;

        //set the cursor at the head of the list
        Location<T> sourceLocation = head;

        //run until the end of list to find the sourceLocation
        while(sourceLocation != null){
            //source found
            if(sourceLocation.locationInfo.compareTo(source) == 0){
                //set the cursor at the head of the location 
                Location<T> destinationLocation = head;

                //run until last location to to find the destinationLocation
                while(destinationLocation != null){
                    //found destination
                    if(destinationLocation.locationInfo.compareTo(destination) == 0){
                        //now set the cursor at the first edge of the sourceLocation
                        Line<T> currentLine = sourceLocation.firstLine;
                        Line<T> newLine = new Line<>(destinationLocation,currentLine);//set new Line
                        sourceLocation.firstLine = newLine;
                        sourceLocation.outdeg++;
                        destinationLocation.indeg++;
                        sourceLocation.neighbour.add(destination);//add location name to arraylist
                        //addEdge(adj,sourceLocation.tag,destinationLocation.tag);
                        //sourceLocation.neighbourLocation.add(destinationLocation); //add location to arraylist
                        //adj.get(hasLocationNo(source)-1).add(hasLocationNo(destination)-1);
                        return true;
                    }
                    destinationLocation = destinationLocation.nextLocation;
                }
            }
            sourceLocation = sourceLocation.nextLocation;
        }
        return false;
    }

    //method to find the locations that connected to v
    public ArrayList<T> getNeighbours(T v){
        //check wether v exist in the list
        if(!hasLocation(v))
            return null;

        ArrayList<T> list = new ArrayList<>();
        //set cursor at the head of the list
        Location<T> temp = head;

        //run until the end of list to find v
        while(temp != null){
            //location v found
            if(temp.locationInfo.compareTo(v)== 0 ){
                //set cursor at the first edge of location v
                Line<T> currentLine = temp.firstLine;

                //run until the last edge to collect data
                while(currentLine != null){
                    list.add(currentLine.toLocation.locationInfo);//add to ArrayList
                    currentLine = currentLine.nextLine;
                }
            }
            temp = temp.nextLocation;
        }
        return list;
    }

    //method to print edge for each Location using LinkedList
    public void printLines(){
        //set cursor at the head of the list
        Location<T> temp = head;
        int no = 0;

        //run until teh end of teh list
        while(temp !=null){
            System.out.println("# "+no+" "+temp.locationInfo+" : ");
            Line<T> currentLine = temp.firstLine;//place cursor at the first edge of the temp location

            //run until the last edge of the location
            while(currentLine != null){
                System.out.println("["+temp.locationInfo+" , "+currentLine.toLocation.locationInfo+"] ");
                currentLine = currentLine.nextLine;
            }
            System.out.println();
            temp = temp.nextLocation;
            no++;
        }
    }

    //method to print edge for each location but using ArrayList<E> declared in Location
    public void printLines2(int num){
        //set cursor at the head of the list
        Location<T> temp = head;
        int no = 0;

        //run until teh end of teh list
        while(temp !=null){
            System.out.println("# "+no+" "+temp.locationInfo+" : ");
            Line<T> currentLine = temp.firstLine;//place cursor at the first edge of the temp location

            //run until the last edge of the location
            for(int i=0 ; i<temp.neighbour.size() ; i++){
                System.out.println("["+temp.locationInfo+" , "+temp.neighbour.get(i)+"] ");
                currentLine = currentLine.nextLine;
            }
            System.out.println();
            temp = temp.nextLocation;
            no++;
        }
    }

    public ArrayList<T> getNeighboursLocation(T v){
        if(head == null)
            return null;

        //start from head
        Location<T> temp = head; 

        //keep repeating until the end of the list
        while(temp != null){
            if(temp.locationInfo.compareTo(v) == 0)//will return 0 if data == v
                return temp.neighbour;
            temp = temp.nextLocation;//keep moving to the next location
        }
        return null;//return false as there are no location v
    }

    public Location getLocation(T v){
        //if list == null
        if(head == null)
            return null;

        //start from head
        Location<T> temp = head; 

        //keep repeating until the end of the list
        while(temp != null){
            if(temp.locationInfo.compareTo(v) == 0)//found location v
                return temp;
            temp = temp.nextLocation;//keep moving to the next location if v not found
        }
        return null;
    }

    public ArrayList<Object> getAllLocationObjects2(){
        ArrayList<Object> list = new ArrayList<>();
        Location<T> temp = head;//let the cursor start from head of list
        int i=0;

        //the cursor will move to the end of the list
        while (temp != null){
            //add location to ArrayList
            list.add(temp);
            temp = temp.nextLocation;//go to next element in the list;
            i++;
        }
        return list;
    }
}
