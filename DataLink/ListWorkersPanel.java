package DataLink;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListWorkersPanel extends JPanel implements DocumentListener{
    private JLabel j1;
    private JLabel j2;
    private JTextField jtfName;
    private JScrollPane jsp;
    private JPanel jPanel;
    private JTable jTable;
    private Object cells[][], columnWorkers[] = {"工号", "姓名", "年龄"};
    private String s;
    private Link link;
    private ArrayList<Workers> worker;

    public ListWorkersPanel(){
        j1 = new JLabel("请输入要查询的姓名");
        j2 = new JLabel();
        jtfName = new JTextField(20);
        jPanel = new JPanel();
        jsp = new JScrollPane();
        s = new String();
        link = MainJFrame.link;
        worker = new ArrayList<Workers>();
        init();
    }

    public void init(){
        add(j1);
        add(jtfName);
        add(j2);
        add(jPanel);
        Document doc = jtfName.getDocument();
        doc.addDocumentListener(this);
    }

    public void getWorker(){
        String strSql = "SELECT * FROM hero where workName like '" + s + "%'";
        worker.removeAll(worker);
        try {
            ResultSet rs = link.getSqlInsert(strSql);
            Workers w;
            while(rs.next()){
                w = new Workers();
                w.setId(rs.getInt(1));
                w.setName(rs.getString(2));
                w.setAge(rs.getInt(3));
                worker.add(w);
            }
            cells = new Object[worker.size()][3];
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showWorker(){
        int i = 0;
        jTable = new JTable(cells, columnWorkers);
        jPanel.removeAll();
        jPanel.add(new JScrollPane(jTable));
        jPanel.validate();
        for(Workers w : worker){
            cells[i][0] = w.getId();
            cells[i][1] = w.getName();
            cells[i][2] = w.getAge();
            i++;
        }
        j2.setText("当前共有" + worker.size() + "条记录");
        jTable.repaint();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        Document d = e.getDocument();
        try {
            s = d.getText(0, d.getLength()).trim();
            if(!s.equals("")) {
                getWorker();
                showWorker();
            }
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        Document d = e.getDocument();
        try {
            s = d.getText(0, d.getLength()).trim();
            if(!s.equals("")) {
                getWorker();
                showWorker();
            }
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        Document d = e.getDocument();
        try {
            s = d.getText(0, d.getLength()).trim();
            if(!s.equals("")) {
                getWorker();
                showWorker();
            }
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
    }
}
