package table;

import model.Point;
import model.tableData.BrezenhemData;
import model.tableData.TableData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class BrezenhemTable implements Table {
    private static final Vector<String> columnsHeader = new Vector<>(List.of("i", "error", "x", "y"));
    private Vector<Vector<String>> data = new Vector<>();
    private List<BrezenhemData> dataList = new ArrayList<>();
    private JTable table;

    public BrezenhemTable() {
        createTableData();
        // Таблица на основе вектора
        table = new JTable(data, columnsHeader);
        // Настройка таблицы table3 - цвет фона, цвет выделения
        table.setForeground(Color.red);
        table.setSelectionForeground(Color.yellow);
        table.setSelectionBackground(Color.blue);
    }

    public void addPoint(BrezenhemData brezenhemData) {
        dataList.add(brezenhemData);
    }

    private void createTableData() {
        data = new Vector<>();
        for (BrezenhemData brezenhemData : dataList) {
            Vector<String> row = new Vector<>();
            BrezenhemData dataFromList = brezenhemData;
            row.add(String.valueOf(dataFromList.getI()));
            row.add(String.valueOf(dataFromList.getError()));
            row.add(String.valueOf(dataFromList.getPoint().getXPoint()));
            row.add(String.valueOf(dataFromList.getPoint().getYPoint()));
            data.add(row);
        }
    }

    @Override
    public JTable getTable() {
        return table;
    }
}
