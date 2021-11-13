package draw;

import algorithm.Algorithm;
import algorithm.Brezenhem;
import algorithm.CDA;
import algorithm.Vu;
import javafx.scene.control.Tab;
import model.Point;
import table.BrezenhemTable;
import table.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

public class Shapes extends JFrame {
    private List<Point> listOfPoints;
    private Algorithm currentAlgorithm;
    private Table table = new BrezenhemTable();

    public static void main( String args[] )
    {
        Shapes app = new Shapes();
        app.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing( WindowEvent e )
                    {
                        System.exit( 0 );
                    }
                }
        );
    }

    private Shapes(){
        listOfPoints = new ArrayList<>();
        Container panel = new Container();
        Container container = getContentPane();

        //Input fields
        JLabel x1Lable = new JLabel("x1:");
        JTextField x1Field = new JTextField();
        JLabel x2Lable = new JLabel("x2:");
        JTextField x2Field = new JTextField();
        JLabel y1Lable = new JLabel("y1:");
        JTextField y1Field = new JTextField();
        JLabel y2Lable = new JLabel("y2:");
        JTextField y2Field = new JTextField();

        //Buttons
        JButton button = new JButton("Нарисовать квадрат");
        button.addActionListener(new ListenerAction());

        //RadioButtons
        JRadioButton cdaBtn = new JRadioButton("ЦДА алгоритм");
        cdaBtn.setSelected(true);
        JRadioButton brezenhemBtn = new JRadioButton("алгоритм Брезенхема");
        JRadioButton vuBtn = new JRadioButton("алгоритм ВУ");

        ButtonGroup group = new ButtonGroup();
        group.add(cdaBtn);
        group.add(brezenhemBtn);
        group.add(vuBtn);

        cdaBtn.addActionListener(e -> {
            clearField();
            currentAlgorithm = new CDA();
        });

        brezenhemBtn.addActionListener(e -> {
            clearField();
            currentAlgorithm = new Brezenhem(
                    Integer.parseInt(x1Field.getText()),
                    Integer.parseInt(x2Field.getText()),
                    Integer.parseInt(y1Field.getText()),
                    Integer.parseInt(y2Field.getText()));
            table = new BrezenhemTable();
        });

        vuBtn.addActionListener(e -> {
            clearField();
            currentAlgorithm = new Vu();
        });

        // Определение менеджера расположения
        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        // Создание горизонтальной группы
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(button)
                        .addComponent(cdaBtn)
                        .addComponent(brezenhemBtn)
                        .addComponent(vuBtn))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(x1Lable)
                        .addComponent(x2Lable))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(x1Field)
                        .addComponent(x2Field))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(y1Lable)
                        .addComponent(y2Lable))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(y1Field)
                        .addComponent(y2Field))
        );

        // Создание вертикальной группы
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button)
                        .addComponent(x1Lable)
                        .addComponent(x1Field)
                        .addComponent(y1Lable)
                        .addComponent(y1Field))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(x2Lable)
                        .addComponent(x2Field)
                        .addComponent(y2Lable)
                        .addComponent(y2Field)
                        .addComponent(cdaBtn))
                .addComponent(brezenhemBtn)
                .addComponent(vuBtn)
        );


        container.setLayout(layout);
        setTitle("Drawing");
        setSize( 530, 530 );
        show();
    }

    class ListenerAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listOfPoints.add(createPointToDraw(currentAlgorithm.getNextPoint()));
            repaint();
        }
    }

    public void paint( Graphics g )
    {
        if(!listOfPoints.isEmpty()) {
            Graphics2D g2d = ( Graphics2D ) g;
            listOfPoints.stream().forEach(point -> {
                g2d.setPaint( Color.decode("#CFB3CD")  );
                g2d.fill(
                        new Rectangle2D.Double( point.getXPoint(), point.getYPoint(), 30, 30 ) );
            });
        } else {
            g.clearRect(0,200,1000,1000);
            g.clearRect(600,0,1000,1000);
        }
    }

    private void clearField() {
        listOfPoints = new ArrayList<>();
        repaint();
    }

    private Point createPointToDraw(Point pointFromAlgorithm) {
        Point pointToDraw = new Point();
        pointToDraw.setXPoint(pointFromAlgorithm.getXPoint() * 30 - 30);
        pointToDraw.setYPoint(530 - (pointFromAlgorithm.getYPoint() * 30));
        return pointToDraw;
    }
}
