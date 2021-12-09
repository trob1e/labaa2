import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Frame {
    private static final int width = 1300;
    private static final int height = 500;
    private JTextField result_field;
    private JTextField x_field;
    private JTextField y_field;
    private JTextField z_field;
    private JLabel image;
    private ButtonGroup radioButtons = new ButtonGroup();
    private Box formula_type = Box.createHorizontalBox();
    private ButtonGroup radioMemoryButtons = new ButtonGroup();
    private Box hBoxMemoryType = Box.createHorizontalBox();
    private JTextField memoryTextField;
    int formula_number = 1;
    private int memoryId = 1;

    private Double mem1 = 0.0;
    private Double mem2 = 0.0;
    private Double mem3 = 0.0;

    public Double formula1(Double x, Double y, Double z) {

        return Math.sin(Math.log(y) + Math.sin(Math.PI)*y*y) * Math.pow(x*x+Math.sin(z)+Math.pow(Math.E, Math.cos(z)), 1 / 4);
    }

    public Double formula2(Double x, Double y, Double z) {
        return Math.pow(Math.cos(Math.pow(Math.E,x)) + Math.log((1+y)*(1+y)) + Math.pow((Math.pow(Math.E,Math.cos(x))+Math.pow(Math.sin(Math.PI),2)*z),1/2)
                +Math.pow(1/x,1/2)+Math.cos(Math.pow(y,2)),Math.sin(z));
    }

    private void addMemoryRadioButton(String buttonName, final int memoryId) {         // радиокнопки для памяти
        JRadioButton button = new JRadioButton(buttonName);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Main_Frame.this.memoryId = memoryId;
                if (memoryId == 1) memoryTextField.setText(mem1.toString());
                if (memoryId == 2) memoryTextField.setText(mem2.toString());
                if (memoryId == 3) memoryTextField.setText(mem3.toString());
            }
        });

        radioMemoryButtons.add(button);
        hBoxMemoryType.add(button);
    }

    private void addRadioButton(String name, final int formula_number)           // радиокнопки для формул
    {
        JRadioButton button = new JRadioButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main_Frame.this.formula_number = formula_number;
                if (formula_number == 1) image.setIcon(new ImageIcon(Main_Frame.class.getResource("formula_1.jpg")));
                if (formula_number == 2) image.setIcon(new ImageIcon(Main_Frame.class.getResource("formula_2.jpg")));
            }
        });
        radioButtons.add(button);
        formula_type.add(button);
    }


}


