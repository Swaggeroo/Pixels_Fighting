import java.awt.*;
import java.util.Random;

public class PixelsFighting extends Thread{
    int waitTime = 10;
    int pixels = 100;
    int zoom = 10;
    double round = 0;
    boolean finished = false;
    Canvas canvas;
    boolean[][] pixelArray;
    boolean multiThreading = false;
    int threads = 1;

    public PixelsFighting() {
        prepare();
        this.start();
    }

    public PixelsFighting(int waitTime, int pixels, int zoom) {
        this.waitTime = waitTime;
        this.pixels = pixels;
        this.zoom = zoom;
        prepare();
        this.start();
    }

    public PixelsFighting(int waitTime, int pixels, int zoom, int threads) {
        this.waitTime = waitTime;
        this.pixels = pixels;
        this.zoom = zoom;
        this.threads = threads;
        multiThreading = true;
        prepare();
        this.start();
    }

    private void prepare(){
        canvas = new Canvas("Pixels Fighting",pixels*zoom,pixels*zoom, Color.RED);
        canvas.setVisible(true);
        canvas.setForegroundColor(Color.GREEN);
        pixelArray = new boolean[pixels][pixels];
        int half = pixels/2;
        for (int i = 0; i< half; i++){
            for (int u = 0; u < pixels; u++){
                pixelArray[i][u] = true;
            }
        }
        for (int i = half; i < pixels; i++){
            for (int u = 0; u < pixels; u++){
                pixelArray[i][u] = false;
            }
        }
    }

    private void fightCalc(){
        int sameNeigbours = 0;
        int falsePixel = 0;
        boolean type;
        Random rand = new Random();
        double zaehler = 0;
        boolean green = false;
        for (int i = 0; i<pixels; i++){
            for (int u = 0; u<pixels; u++){
                type = pixelArray[i][u];
                sameNeigbours = 0;
                falsePixel=0;
                try {
                    if (type == pixelArray[i-1][u]){
                        sameNeigbours++;
                    }
                }catch (Exception e){
                    falsePixel--;
                }
                try {
                    if (type == pixelArray[i][u-1]){
                        sameNeigbours++;
                    }
                }catch (Exception e){
                    falsePixel--;
                }
                try {
                    if (type == pixelArray[i+1][u]){
                        sameNeigbours++;
                    }
                }catch (Exception e){
                    falsePixel--;
                }
                try {
                    if (type == pixelArray[i][u+1]){
                        sameNeigbours++;
                    }
                }catch (Exception e){
                    falsePixel--;
                }

                if (sameNeigbours >= 5+falsePixel){
                    break;
                }

                try {
                    if (type == pixelArray[i+1][u+1]){
                        sameNeigbours++;
                    }
                }catch (Exception e){
                    falsePixel--;
                }
                try {
                    if (type == pixelArray[i-1][u+1]){
                        sameNeigbours++;
                    }
                }catch (Exception e){
                    falsePixel--;
                }
                try {
                    if (type == pixelArray[i-1][u-1]){
                        sameNeigbours++;
                    }
                }catch (Exception e){
                    falsePixel--;
                }
                try {
                    if (type == pixelArray[i+1][u-1]){
                        sameNeigbours++;
                    }
                }catch (Exception e){
                    falsePixel--;
                }

                if (sameNeigbours == 0){
                    pixelArray[i][u] = !type;
                }else if (sameNeigbours == 8+falsePixel){
                    pixelArray[i][u] = type;
                }else {
                    if (rand.nextInt(9+falsePixel) <= sameNeigbours){
                        pixelArray[i][u] = type;
                    }else{
                        pixelArray[i][u] = !type;
                    }
                }
                if (pixelArray[i][u]){
                    canvas.setForegroundColor(Color.GREEN);
                    canvas.fillRectangle(i*zoom,u*zoom,zoom,zoom);
                    zaehler++;
                    green = true;
                }else {
                    canvas.setForegroundColor(Color.RED);
                    canvas.fillRectangle(i*zoom,u*zoom,zoom,zoom);
                }
            }
        }
        if (!green || zaehler >= pixels*pixels){
            finished = true;
        }
    }

    private void makeUI(int von, int bis){
        double zaehler = 0;
        boolean green = false;
        for (int i = von; i<=bis; i++){
            for (int u = 0; u<pixels; u++){
                if (pixelArray[i][u]) {
                    //Grün
                    canvas.setForegroundColor(Color.GREEN);
                    canvas.fillRectangle(i*zoom,u*zoom,zoom,zoom);
                    zaehler++;
                    green = true;
                }else{
                    canvas.setForegroundColor(Color.RED);
                    canvas.fillRectangle(i*zoom,u*zoom,zoom,zoom);
                }
            }
        }
        if (!green || zaehler >= pixels*pixels){
            finished = true;
        }
    }

    @Override
    public void run() {
        while(!finished){
            round++;
            System.out.println(round);
            if (!multiThreading){
                fightCalc();
                try {
                    Thread.sleep(waitTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                Thread1[] startedThreads = new Thread1[threads];
                int haepchen = pixels/threads;
                for (int i = 0; i<startedThreads.length;i++){
                    startedThreads[i] = new Thread1(haepchen*i,haepchen*(i+1)-1,true,canvas,pixelArray,pixels,zoom,i);
                    startedThreads[i].run();
                }
                /*boolean threadsFinished = false;
                while(!threadsFinished){
                    int threadsHasFinished = 0;
                    for (int i = 0; i<startedThreads.length;i++){
                        if (startedThreads[i] == null){
                            threadsHasFinished++;
                        }
                    }
                    if (threadsHasFinished >= threads){
                        threadsFinished = true;
                    }
                    try {
                        Thread.sleep(waitTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }*/
            }
        }
    }
}
