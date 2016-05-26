package step6.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import step6.vo.*;

public class SESUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lbl_title;
	private JPanel p_west;
	private JPanel p_west_1;
	private JPanel p_west_2;
	private JPanel p_west_3;
	private JPanel p_west_4;
	private JLabel lbl_name;
	private JTextField tf_name;
	private JLabel lbl_age;
	private JTextField tf_age;
	private JLabel lbl_jumin;
	private JTextField tf_jumin;
	private JLabel lbl_major;
	private JTextField tf_major;
	private JPanel p_west_5;
	private JPanel p_west_6;
	private JLabel lbl_stdNo;
	private JTextField tf_stdNo;
	private JLabel lbl_field;
	private JTextField tf_field;
	private JScrollPane sp_center;
	private JList li_humanList;
	private JPanel p_south;
	private JComboBox cb_humanSelect;
	private JButton btn_insert;
	private JButton btn_modify;
	private JButton btn_delete;
	private JButton btn_search;
	private JButton btn_ok;
	private JButton btn_cancel;
	private String preAction;
	private String selectedHuman;
	private Human selectedValue;
	private SESClientManager manager = new SESClientManager();


	/**
	 * Create the frame.
	 */
	public SESUI() {
		setTitle("\u25C0Soft Engineer School Manager\u25B6");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		lbl_title = new JLabel("Soft Engineer School Manager");
		lbl_title.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lbl_title.setOpaque(true);
		lbl_title.setBackground(Color.ORANGE);
		lbl_title.setFont(new Font("굴림", Font.BOLD, 26));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbl_title, BorderLayout.NORTH);
		
		p_west = new JPanel();
		contentPane.add(p_west, BorderLayout.WEST);
		p_west.setLayout(new GridLayout(6, 1, 0, 0));
		
		p_west_1 = new JPanel();
		p_west.add(p_west_1);
		
		lbl_name = new JLabel("\uC774\uB984");
		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_name.setPreferredSize(new Dimension(60, 15));
		p_west_1.add(lbl_name);
		
		tf_name = new JTextField();
		p_west_1.add(tf_name);
		tf_name.setColumns(10);
		
		p_west_2 = new JPanel();
		p_west.add(p_west_2);
		
		lbl_age = new JLabel("\uB098\uC774");
		lbl_age.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_age.setPreferredSize(new Dimension(60, 15));
		p_west_2.add(lbl_age);
		
		tf_age = new JTextField();
		p_west_2.add(tf_age);
		tf_age.setColumns(10);
		
		p_west_3 = new JPanel();
		p_west.add(p_west_3);
		
		lbl_jumin = new JLabel("\uC8FC\uBBFC\uBC88\uD638");
		lbl_jumin.setPreferredSize(new Dimension(60, 15));
		lbl_jumin.setHorizontalAlignment(SwingConstants.CENTER);
		p_west_3.add(lbl_jumin);
		
		tf_jumin = new JTextField();
		p_west_3.add(tf_jumin);
		tf_jumin.setColumns(10);
		
		p_west_4 = new JPanel();
		p_west.add(p_west_4);
		
		lbl_major = new JLabel("\uC804\uACF5");
		lbl_major.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_major.setPreferredSize(new Dimension(60, 15));
		p_west_4.add(lbl_major);
		
		tf_major = new JTextField();
		p_west_4.add(tf_major);
		tf_major.setColumns(10);
		
		p_west_5 = new JPanel();
		p_west.add(p_west_5);
		
		lbl_stdNo = new JLabel("\uD559\uBC88");
		lbl_stdNo.setPreferredSize(new Dimension(60, 15));
		lbl_stdNo.setHorizontalAlignment(SwingConstants.CENTER);
		p_west_5.add(lbl_stdNo);
		
		tf_stdNo = new JTextField();
		p_west_5.add(tf_stdNo);
		tf_stdNo.setColumns(10);
		
		p_west_6 = new JPanel();
		p_west.add(p_west_6);
		
		lbl_field = new JLabel("\uBD80\uC11C");
		lbl_field.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_field.setPreferredSize(new Dimension(60, 15));
		p_west_6.add(lbl_field);
		
		tf_field = new JTextField();
		p_west_6.add(tf_field);
		tf_field.setColumns(10);
		
		sp_center = new JScrollPane();
		sp_center.setBorder(new TitledBorder(null, "\uB4F1\uB85D\uC815\uBCF4", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		contentPane.add(sp_center, BorderLayout.CENTER);
		
		li_humanList = new JList();
		li_humanList.addMouseListener(new MouseHandler());
		sp_center.setViewportView(li_humanList);
		
		p_south = new JPanel();
		p_south.setForeground(Color.LIGHT_GRAY);
		contentPane.add(p_south, BorderLayout.SOUTH);
		
		cb_humanSelect = new JComboBox();
		cb_humanSelect.setOpaque(false);
		cb_humanSelect.setBackground(Color.LIGHT_GRAY);
		cb_humanSelect.addActionListener(this);
		cb_humanSelect.setModel(new DefaultComboBoxModel(new String[] {"\uB300\uC0C1\uC120\uD0DD", "\uAD50\uC218", "\uC5F0\uC218\uC0DD", "\uC6B4\uC601\uC9C4"}));
		p_south.add(cb_humanSelect);
		
		btn_insert = new JButton("\uB4F1\uB85D");
		btn_insert.addActionListener(this);
		p_south.add(btn_insert);
		
		btn_modify = new JButton("\uC218\uC815");
		btn_modify.addActionListener(this);
		p_south.add(btn_modify);
		
		btn_search = new JButton("\uAC80\uC0C9");
		btn_search.addActionListener(this);
		p_south.add(btn_search);
		
		btn_delete = new JButton("\uC0AD\uC81C");
		btn_delete.addActionListener(this);
		p_south.add(btn_delete);
		
		btn_ok = new JButton("\uD655\uC778");
		btn_ok.addActionListener(this);
		p_south.add(btn_ok);
		
		btn_cancel = new JButton("\uCDE8\uC18C");
		btn_cancel.addActionListener(this);
		p_south.add(btn_cancel);
		
		initButton(true);
		initField("All", false);
		showList(manager.getHumanList());
		
		setVisible(true);
	}
	
	public void showList(ArrayList<Human> list){
		Object humans [] = list.toArray();
		li_humanList.setListData(humans);
	}
	
	public void initField(String type, boolean status){
		tf_name.setEditable(status);
		tf_age.setEditable(status);
		tf_jumin.setEditable(status);
		if(type.equals("All")){
			tf_major.setEditable(status);
			tf_stdNo.setEditable(status);
			tf_field.setEditable(status);
		} else if(type.equals("교수")){
			tf_major.setEditable(status);			
		} else if(type.equals("연수생")){
			tf_stdNo.setEditable(status);			
		} else if(type.equals("운영진")){
			tf_field.setEditable(status);			
		}
		
	}
	
	public void initButton(boolean status){
		btn_insert.setEnabled(status);
		btn_modify.setEnabled(status);
		btn_delete.setEnabled(status);
		btn_search.setEnabled(status);
		btn_ok.setEnabled(!status);
		btn_cancel.setEnabled(!status);
	}
	
	public void clearTextField(){
		tf_name.setText("");
		tf_age.setText("");
		tf_jumin.setText("");
		tf_major.setText("");
		tf_stdNo.setText("");
		tf_field.setText("");
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == btn_insert){
			selectedHuman = (String)cb_humanSelect.getSelectedItem();
			if(selectedHuman.equals("대상선택")){
				JOptionPane.showMessageDialog(this, "등록할 대상을 먼저 선택하세요!");
			} else {
				clearTextField();
				initButton(false);
				initField(selectedHuman, true);
				preAction = "등록";
			}
		} else if(source == btn_modify) {
			if(selectedValue == null){
				JOptionPane.showMessageDialog(this, "수정할 데이터를 먼저 선택해주세요!");
			} else {
				if(selectedValue instanceof Professor) selectedHuman ="교수";
				if(selectedValue instanceof Trainee) selectedHuman ="연수생";
				if(selectedValue instanceof Staff) selectedHuman ="운영진";
				
				initField(selectedHuman, true);
				tf_jumin.setEditable(false);
				initButton(false);
				li_humanList.setEnabled(false);
				preAction = "수정";
			}
		} else if(source == btn_search) {
			clearTextField();
			tf_jumin.setEditable(true);
			initButton(false);
			preAction = "검색";
		} else if(source == btn_delete) {
			if(selectedValue == null){
				JOptionPane.showMessageDialog(this, "삭제할 데이터를 먼저 선택해주세요!");
			} else {
				String jumin = tf_jumin.getText();
				boolean result = manager.deleteHuman(jumin);
				showList(manager.getHumanList());
				clearTextField();
			}
		} else if(source == btn_ok) {
			Human h = null;
			String name = tf_name.getText();
			int age = tf_age.getText().trim().length() > 0 ? Integer.parseInt(tf_age.getText()) : 0;
			String jumin = tf_jumin.getText();
			
			switch(preAction){
				case "등록": 
					if(selectedHuman.equals("교수")) {		
						String major = tf_major.getText();
						h = new Professor(name, age, jumin, major);
					} else if(selectedHuman.equals("연수생")) {
						String stdNo = tf_stdNo.getText();
						h = new Trainee(name, age, jumin, stdNo);
					} else if(selectedHuman.equals("운영진")) {
						String field = tf_field.getText();
						h = new Staff(name, age, jumin, field);
					}
					boolean result = manager.insertHuman(h);
					if(!result){
						JOptionPane.showMessageDialog(this, "이미 등록된 주민번호가 존재합니다.");
						clearTextField();
					}
					break;
				case "수정":					
					Human newHuman = null;
					if(selectedValue instanceof Professor){
						String newMajor = tf_major.getText();
						newHuman = new Professor(name, age, jumin, newMajor);
					} else if(selectedValue instanceof Trainee){
						String newStdNo = tf_stdNo.getText();
						newHuman = new Trainee(name, age, jumin, newStdNo);
					} else {
						String newField = tf_field.getText();
						newHuman = new Staff(name, age, jumin, newField);
					}
					manager.updateHuman(newHuman);
					break;
				case "검색":
					Human foundHuman = manager.findHuman(jumin);
					if(foundHuman == null){
						JOptionPane.showMessageDialog(this, "검색 결과가 없습니다.");
						clearTextField();
					} else {
						setTextFieldValue(foundHuman);
					}
					break;
			}//switch
			li_humanList.setEnabled(true);
			initField("All", false);
			initButton(true);
			showList(manager.getHumanList());
		} else if(source == btn_cancel){
			clearTextField();
			initField("All", false);
			initButton(true);
			li_humanList.setEnabled(true);
			selectedValue = null;
		}
	}
	
	private void setTextFieldValue(Human h){
		tf_name.setText(h.getName());
		tf_age.setText(Integer.toString(h.getAge()));
		tf_jumin.setText(h.getJumin());
		if(h instanceof Professor){
			Professor p = (Professor)h;
			tf_major.setText(p.getMajor());
		} else if(h instanceof Trainee){
			Trainee t = (Trainee)h;
			tf_stdNo.setText(t.getStdNo());
		} else {
			Staff s = (Staff)h;
			tf_field.setText(s.getField());
		}
	}
	
	private class MouseHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent me){
			selectedValue = (Human)li_humanList.getSelectedValue();
			clearTextField();
			setTextFieldValue(selectedValue);
		}
	}
	
}
