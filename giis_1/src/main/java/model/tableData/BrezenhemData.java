package model.tableData;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.Point;

@AllArgsConstructor
@Data
public class BrezenhemData implements TableData {
    private int i, error;
    private Point point;
}
