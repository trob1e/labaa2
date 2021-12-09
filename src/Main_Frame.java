import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Frame extends JFrame {
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

    public Main_Frame() {
        super("Вычисление формулы");
        setSize(width, height);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - width) / 2,
                (kit.getScreenSize().height - height) / 2);


        Box picture = Box.createHorizontalBox();                    // область с картинкой
        picture.add(Box.createVerticalGlue());
        picture.add(Box.createHorizontalGlue());
        image = new JLabel(new ImageIcon(Main_Frame.class.getResource("formula_1.jpg")));
        picture.add(image);
        picture.add(Box.createHorizontalGlue());
        picture.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        formula_type.add(Box.createHorizontalGlue());              // область с выбором формул
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        formula_type.add(Box.createHorizontalGlue());
        formula_type.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        Box data = Box.createHorizontalBox();                 // область с полями ввода значений
        x_field = new JTextField("0", 10);
        x_field.setMaximumSize(x_field.getPreferredSize());
        y_field = new JTextField("0", 10);
        y_field.setMaximumSize(x_field.getPreferredSize());
        z_field = new JTextField("0", 10);
        z_field.setMaximumSize(x_field.getPreferredSize());
        JLabel x_label = new JLabel("X:", JLabel.LEFT);
        JLabel y_label = new JLabel("Y:", JLabel.CENTER);
        JLabel z_label = new JLabel("Z:", JLabel.RIGHT);
        //data.add(Box.createHorizontalGlue());
        data.add(x_label);
        data.add(Box.createHorizontalStrut(10));
        data.add(x_field);
        data.add(Box.createHorizontalGlue());
        data.add(Box.createHorizontalStrut(100));
        data.add(y_label);
        data.add(Box.createHorizontalStrut(10));
        data.add(y_field);
        data.add(Box.createHorizontalGlue());
        data.add(Box.createHorizontalStrut(100));
        data.add(z_label);
        data.add(Box.createHorizontalStrut(10));
        data.add(z_field);
        //data.add(Box.createHorizontalGlue());
        data.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        Box result_area = Box.createHorizontalBox();          // область для результата
        result_area.add(Box.createHorizontalGlue());
        JLabel resultlabel = new JLabel("Результат:");
        result_field = new JTextField("0", 15);
        result_field.setMaximumSize(result_field.getPreferredSize());
        result_area.add(resultlabel);
        result_area.add(Box.createHorizontalStrut(10));
        result_area.add(result_field);
        result_area.add(Box.createHorizontalGlue());
        result_area.setBorder(BorderFactory.createLineBorder(Color.PINK));

        Box actions = Box.createHorizontalBox();                        // область для действий
        JButton calc_button = new JButton("Вычислить");
        calc_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                //Преобразование введенных строк в числа с плавающей точкой может
                //спровоцировать исключительную ситуацию при неправильном формате чисел,
                //поэтому необходим блок try-catch
                try {
                    //Получить значение X
                    Double x = Double.parseDouble(x_field.getText());
                    //Получить значение Y
                    Double y = Double.parseDouble(y_field.getText());
                    //Получить значение Z
                    Double z = Double.parseDouble(z_field.getText());
                    // Результат
                    Double result;

                    //Вычислить результат
                    if (formula_number == 1)
                        result = formula1(x, y, z);
                    else
                        result = formula2(x, y, z);
                    //Установить текст надписи равным результату
                    result_field.setText(result.toString());
                } catch (NumberFormatException ex) {
                    //В случае исключительной ситуации показать сообщение
                    JOptionPane.showMessageDialog(Main_Frame.this, "Ошибка в" +
                                    "формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton clean_button = new JButton("Очистить");
        clean_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                x_field.setText("0");
                y_field.setText("0");
                z_field.setText("0");
                result_field.setText("0");
            }
        });
        actions.add(Box.createHorizontalGlue());
        actions.add(calc_button);
        actions.add(Box.createHorizontalStrut(10));
        actions.add(clean_button);
        actions.add(Box.createHorizontalGlue());

        hBoxMemoryType.add(Box.createHorizontalGlue());
        addMemoryRadioButton("Память 1", 1);
        addMemoryRadioButton("Память 2", 2);
        addMemoryRadioButton("Память 3", 3);
        radioMemoryButtons.setSelected(radioMemoryButtons.getElements().nextElement().getModel(), true);
        hBoxMemoryType.add(Box.createHorizontalGlue());


        Box memory_result = Box.createHorizontalBox();
        memory_result.add(Box.createHorizontalGlue());
        JButton MC = new JButton("MC");

        MC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (memoryId == 1) mem1 = (double) 0;
                if (memoryId == 2) mem2 = (double) 0;
                if (memoryId == 3) mem3 = (double) 0;
                memoryTextField.setText("0.0");
            }
        });

        JButton M_plus = new JButton("M+");
        M_plus.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Double result = Double.parseDouble(result_field.getText());

                    if (memoryId == 1) {
                        mem1 += result;
                        memoryTextField.setText(mem1.toString());
                    }
                    if (memoryId == 2) {
                        mem2 += result;
                        memoryTextField.setText(mem2.toString());
                    }
                    if (memoryId == 3) {
                        mem3 += result;
                        memoryTextField.setText(mem3.toString());
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Main_Frame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "" +
                                    "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        memoryTextField = new JTextField("0.0", 15);
        memoryTextField.setMaximumSize(memoryTextField.getPreferredSize());

        memory_result.add(MC);
        memory_result.add(Box.createHorizontalStrut(10));
        memory_result.add(memoryTextField);
        memory_result.add(Box.createHorizontalStrut(10));
        memory_result.add(M_plus);
        memory_result.add(Box.createHorizontalGlue());


        //засовываем все боксы в контентбокс
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(picture);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(formula_type);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(data);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(result_area);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(actions);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hBoxMemoryType);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(memory_result);

        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
}


