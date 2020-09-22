public class BirthdayParadox {
    private static java.util.Random generator = new java.util.Random();
    public static Statistics runExperiments(int range, int numberOfRuns){

        Statistics s = new Statistics(numberOfRuns);
        for (int i = 0; i < numberOfRuns ; i++)
        {
            int temp = oneRun(range);
            s.updateStatistics(temp);
        }
        return s;
    }
    private static int oneRun(int range){
        boolean[] Visited = new boolean[range];
        int count =0 ;

        for (int i = 0 ; i < range ; i++)
        {
            Visited[i] = false;
        }
        while(true) {
            int rand = generator.nextInt(range - 1);
            if (!Visited[rand]) {
                count += 1;
                Visited[rand] = true;
            } else {
                return count;
            }
        }

    }
    public static void main(String[] args)
    {
        int step = 0;
        int runs = 0;
        int limit = 0 ;
        if(args.length == 0)
        {
            step = 100;
            runs = 1000;
            limit = 10000;
        }
        else if( args.length == 3)
        {
            step = Integer.parseInt(args[0]);
            limit = Integer.parseInt(args[1]);
            runs = Integer.parseInt(args[2]);
        }
        for (int size = step ; size <= limit ; size+=step) {
            System.out.println("Set size:" + size);
            System.out.println(runExperiments(size, runs).toString());
        }
    }

}

class Statistics {
    private int Minimum;
    private int Maximum;
    private int [] results;
    private int sum ;
    private int count ;
    private int track;

    public Statistics(int numberOfRuns){
        this.Minimum = Integer.MAX_VALUE;
        this.Maximum = 0;
        this.results = new int[numberOfRuns];
        this.count = numberOfRuns;
        this.track = 0;

    }
    public void updateStatistics(int value){

        if (value > this.Maximum)
        {
            this.Maximum = value;
        }
        if (value < this.Minimum)
        {
            this.Minimum = value;
        }
        this.sum += value;
        this.results[this.track] = value;
        this.track++;
    }

    public double average(){
        return this.sum / this.count ;
    }

    public double standardDeviation(){
        double Sum =0 ;
        double avg = this.average();
        for (int i=0 ; i < this.results.length ; i++)
        {
            Sum += Math.pow(this.results[i] - avg , 2);
        }
        return Math.sqrt(Sum / this.count);
    }

    public String toString(){

        return "Minimum: " + this.Minimum +"\nMaximum: " + this.Maximum +"\nAverage: " + String.format("%.2f", this.average()) +"\nStandard Deviation: " + String.format("%.2f" ,this.standardDeviation());
    }
}
