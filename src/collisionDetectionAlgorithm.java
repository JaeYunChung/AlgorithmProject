import java.util.*;

public class collisionDetectionAlgorithm {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        //points :[[3, 2], [6, 4], [4, 7], [1, 4]]	routes : [[4, 2], [1, 3], [2, 4]]
        Set<Integer> sameStart = new HashSet<>();
        Set<Integer> reduntdancy = new HashSet<>();
        for (int i = 0; i < routes.length; i++)
        {
            for(int j = 0; j < routes[i].length; j++){
                routes[i][j]--;
            }
            if (sameStart.contains(routes[i][0])&& (!reduntdancy.contains(routes[i][0])))
            {
                answer++;
                reduntdancy.add(routes[i][0]);
            }
            else sameStart.add(routes[i][0]);
        } // routes : [[3, 1], [0, 2], [1, 3]]
        Point[] point = Arrays.stream(routes)
                .map(route -> new Point(points, route))
                .toArray(Point[]::new);
        while(isFinish(point))
        {
            Set<Point> compareSet = new HashSet<>(){
                @Override
                public boolean contains(Object obj){
                    for(Point p : this){
                        if(p.equals(obj)) return true;
                    }return false;
                }
            };
            Set<Point> reduntSet = new HashSet<>()
                {
                    @Override
                    public boolean contains (Object obj){
                    for (Point p : this) {
                        if (p.equals(obj)) return true;
                    }
                    return false;
                }
            };
            for(Point p : point)p.move();
            for(Point p : point)
            {
                if(p.remainDistance() == 0 && p.arrive)continue;
                if(compareSet.contains(p)&&(!reduntSet.contains(p))){
                    answer++;
                    reduntSet.add(p);
                }
                else compareSet.add(p);
                if(p.remainDistance() == 0) p.arrive = true;
            }

        }
        return answer;
    }
    boolean isFinish(Point[] point)
    {
        for(Point p : point){
            if (p.remainDistance() != 0)return true;
        }return false;
    }
    public static class Point
    {
        boolean arrive = false;
        int[][] points;
        int[] routes;
        int presentRoute = 1;
        int y, x;
        public Point(int[][] points, int[] routes)
        {
            this.points = points;
            this.routes = routes;
            this.y = points[routes[0]][0];
            this.x = points[routes[0]][1];
        }
        int remainDistance()
        {
            int yDist = points[routes[presentRoute]][0] - y > 0
                    ? points[routes[presentRoute]][0] - y
                    : y - points[routes[presentRoute]][0];
            int xDist = points[routes[presentRoute]][1] - x > 0
                    ? points[routes[presentRoute]][1] - x
                    : x - points[routes[presentRoute]][1];
            return yDist + xDist + (routes.length - presentRoute - 1);
        }
        void move()
        {
            if(points[routes[presentRoute]][0] == y && points[routes[presentRoute]][1] == x
            && presentRoute + 1 != routes.length) presentRoute++;

            if (points[routes[presentRoute]][0] - y != 0)
            {
                if(points[routes[presentRoute]][0] - y < 0) y--;
                else y++;
            }
            else if (points[routes[presentRoute]][1] - x != 0)
            {
                if(points[routes[presentRoute]][1] - x < 0) x--;
                else x++;
            }
            else return;
        }
        @Override
        public boolean equals(Object obj)
        {
            if(this == obj) return true;
            else if (obj == null || obj.getClass() != this.getClass()) return false;
            Point point = (Point) obj;
            return (this.y == point.y) && (this.x == point.x);
        }
    }
}
