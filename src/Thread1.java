public class Thread1 implements Runnable{

    int von;
    int bis;
    boolean first;
    Canvas canvas;
    boolean[][] pixelArray;
    int pixels;
    int zoom;
    int threadNumber;

    public Thread1(int von, int bis, boolean first, Canvas canvas, boolean[][] pixelArray, int pixels,int zoom, int threadNumber) {
        this.von = von;
        this.bis = bis;
        this.first = first;
        this.canvas = canvas;
        this.pixelArray = pixelArray;
        this.pixels = pixels;
        this.zoom = zoom;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        System.out.println("Start Thread: "+threadNumber);
        FightCalc fightCalc = new FightCalc();
        fightCalc.figtToDO(von,bis,pixelArray,pixels,canvas,zoom);
        System.out.println("Ending Thread: "+threadNumber);
    }
}
