package model.tableData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Point;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CDAData implements TableData {
    private Integer i;
    private Double xDouble;
    private Double yDouble;
    private Point point;
}
