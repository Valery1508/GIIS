package draw;

import javafx.scene.control.RadioButton;
import model.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

public class Shapes extends JFrame {
    private List<Point> listOfPoints;
    private int x = 0, y = 500;

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

    public Shapes()
    {
        super( "Drawing 2D shapes" );
        listOfPoints = new ArrayList<>();
        Container container = getContentPane();
        container.setLayout(new FlowLayout( FlowLayout.LEFT, 10, 10));

        JButton button = new JButton("Обычная кнопка");
        button.addActionListener(new ListenerAction());
        container.add(button);

        setSize( 530, 530 );
        show();
    }

    class ListenerAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listOfPoints.add(new Point(x, y));
            repaint();
            x += 30;
            y -= 30;
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
        }
    }

    private void createRadioButton() {
        JRadioButton cdaBtn = new JRadioButton("ЦДА алгоритм");
        cdaBtn.setSelected(true);
        JRadioButton brezenhemBtn = new JRadioButton("алгоритм Брезенхема");
        JRadioButton vuBtn = new JRadioButton("алгоритм ВУ");

        ButtonGroup group = new ButtonGroup();
        group.add(cdaBtn);
        group.add(brezenhemBtn);
        group.add(vuBtn);

        cdaBtn.setActionCommand();
        cdaBtn.addActionListener(this);
        brezenhemBtn.addActionListener(this);
        vuBtn.addActionListener(this);
    }
}
