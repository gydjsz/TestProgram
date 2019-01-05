package DataLink;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWorkerPanel extends JPanel {
    private JLabel jName;
    private JLabel jAge;
    private JLabel jFindName;
    private JTextField jtfName;
    private JTextField jtfAge;
    private JButton jbAdd;

    public AddWorkerPanel(){
        jName = new JLabel("姓名", JLabel.CENTER);
        jAge = new JLabel("年龄", JLabel.CENTER);
        jFindName = new JLabel("请输入要查询的姓名：");
        jtfName = new JTextField(20);
        jtfAge = new JTextField(20);
        jbAdd = new JButton("确认添加");
        init();
    }

    public void init(){
        JPanel jpInput = new JPanel();
        JPanel jpSearch = new JPanel();
        jpInput.setLayout(new GridLayout(2, 2));
        jpInput.add(jName);
        jpInput.add(jtfName);
        jpInput.add(jAge);
        jpInput.add(jtfAge);
        jpSearch.add(jbAdd);
        setLayout(new BorderLayout());
        add(jpInput, BorderLayout.NORTH);
        add(jpSearch, BorderLayout.CENTER);
        Component c = this.getComponent(1);
        jbAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strName = jtfName.getText();
                String strAge = jtfAge.getText();
                if(!strName.equals("") && !strAge.equals("")) {
                    int age = Integer.parseInt(strAge);
                    if(age > 18 && age <= 120) {
                        MainJFrame.link.addData(strName, age);
                        JOptionPane.showMessageDialog(c, "插入成功");
                    }
                    else
                        JOptionPane.showMessageDialog(c, "插入失败,请正确填写信息");
                }
                else
                    JOptionPane.showMessageDialog(c, "插入失败,请正确填写信息");
                jtfName.setText(null);
                jtfAge.setText(null);
            }
        });
    }

}
