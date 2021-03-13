import java.awt.*;
import java.util.Random;

public class FightCalc {
    public FightCalc() {
    }

    public void figtToDO(int von, int bis, boolean[][] pixelArray, int pixels,Canvas canvas,int zoom){
        int sameNeigbours = 0;
        int falsePixel = 0;
        boolean type;
        Random rand = new Random();
        for (int i = von; i<=bis; i++){
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
                }else {
                    canvas.setForegroundColor(Color.RED);
                    canvas.fillRectangle(i*zoom,u*zoom,zoom,zoom);
                }
            }
        }
    }

}
