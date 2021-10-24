package algorithm;

import model.Point;
import model.tableData.CDAData;
import model.tableData.TableData;

public interface Algorithm {
    Point getNextPoint();

    TableData getTableData();
}
