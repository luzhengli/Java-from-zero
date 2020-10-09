public class Instance{
    public static void main(String[] args) {
        Point p = new Point(2, -1);
        System.out.println(p.x());
        System.out.println(p.y());
    }
}


record Point(int x, int y){

}