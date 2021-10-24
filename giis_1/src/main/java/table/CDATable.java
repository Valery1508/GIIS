package table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.tableData.CDAData;

public class CDATable {
    public CDATable createTable(CDAData cdaData){
        /*ObservableList<CDAData> data = FXCollections.observableArrayList(

                new Person("Tom", 34),
                new Person("Bob", 22),
                new Person("Sam", 28),
                new Person("Alice", 29)
        );

        TableView<CDAData> table = new TableView<CDAData>(data);
        table.setPrefWidth(250);
        table.setPrefHeight(200);

        // столбец для вывода имени
        TableColumn<CDAData, String> nameColumn = new TableColumn<CDAData, String>("Name");
        // определяем фабрику для столбца с привязкой к свойству name
        nameColumn.setCellValueFactory(new PropertyValueFactory<CDAData, String>("name"));
        // добавляем столбец
        table.getColumns().add(nameColumn);

        // столбец для вывода возраста
        TableColumn<CDAData, Integer> ageColumn = new TableColumn<CDAData, Integer>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<CDAData, Integer>("age"));
        table.getColumns().add(ageColumn);*/
        return null;
    }
}
