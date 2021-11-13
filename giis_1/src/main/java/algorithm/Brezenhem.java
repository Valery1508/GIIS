package algorithm;

import model.Point;
import model.tableData.BrezenhemData;
import model.tableData.TableData;

import java.util.ArrayList;
import java.util.List;

public class Brezenhem implements Algorithm{
    private int dx, dy, x, y, error;
    private int a, b, da, db;
    private boolean xWay;
    private Point currentPoint, nextPoint;
    private List<Point> allPoints;
    private List<BrezenhemData> brezenhemData;

    public Brezenhem(int x1, int x2, int y1, int y2) {
        this.dx = x2 - x1;
        this.dy = y2 - y1;
        this.x = x1;
        this.y = y1;
        this.xWay = dx > dy;
        if(xWay) {
            this.a = x;
            this.b = y;
            this.da = dx;
            this.db = dy;
        } else {
            this.a = y;
            this.b = x;
            this.da = dy;
            this.db = dx;
        }
        allPoints = new ArrayList<>();
        brezenhemData = new ArrayList<>();
        algorithmExecution();
    }

    private void algorithmExecution() {
        int i = 0;
        error = (2 * db) - da;
        while (i <= da) {
            if(error >= 0) {
                b++;
                error = error - (2 * da);
            }
            a++;
            error = error + (2 * db);
            i++;
            Point currentPoint = xWay ? new Point(a, b) : new Point(b, a);
            allPoints.add(currentPoint);
            brezenhemData.add(new BrezenhemData(i, error, currentPoint));
        }
    }


    @Override
    public Point getNextPoint() {
        if(nextPoint == null){
            nextPoint = allPoints.get(0);
        } else {
            int indexOfCurrentPoint = allPoints.indexOf(currentPoint);
            nextPoint = allPoints.get(indexOfCurrentPoint + 1);
        }
        currentPoint = nextPoint;
        return nextPoint;
    }

    @Override
    public TableData getTableData() {
        return brezenhemData.stream().filter(data -> data.getPoint() == nextPoint).findFirst().get();
    }
}
