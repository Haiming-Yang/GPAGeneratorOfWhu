package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Controller.ScoreIO;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI {

	private JFrame frmGpagenerator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frmGpagenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGpagenerator = new JFrame();
		frmGpagenerator.setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/SteveYang/Documents/Programming/Java/GPAGenerator/Resource/icon.jpeg"));
		frmGpagenerator.setResizable(false);
		frmGpagenerator.setTitle("GPAGenerator via SyeveYang");
		frmGpagenerator.setBounds(100, 100, 450, 300);
		frmGpagenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGpagenerator.getContentPane().setLayout(null);
		
		JButton btnHtml = new JButton("读取HTML并处理");
		
		btnHtml.setBounds(43, 198, 143, 58);
		frmGpagenerator.getContentPane().add(btnHtml);
		
		JLabel lblGpa = new JLabel("GPA          ：");
		lblGpa.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblGpa.setBounds(52, 46, 134, 36);
		frmGpagenerator.getContentPane().add(lblGpa);
		
		JButton btnXls = new JButton("保存结果到XLS");
		
		btnXls.setBounds(258, 198, 143, 58);
		btnXls.setEnabled(false);
		frmGpagenerator.getContentPane().add(btnXls);
		
		JLabel lblAverage = new JLabel("加权平均分：");
		lblAverage.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblAverage.setBounds(52, 120, 134, 36);
		frmGpagenerator.getContentPane().add(lblAverage);
		
		JLabel lblResultOfGpa = new JLabel("成绩未处理");
		lblResultOfGpa.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblResultOfGpa.setBounds(275, 48, 126, 32);
		frmGpagenerator.getContentPane().add(lblResultOfGpa);
		
		JLabel lblResultOfAverage = new JLabel("成绩未处理");
		lblResultOfAverage.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblResultOfAverage.setBounds(275, 122, 126, 32);
		frmGpagenerator.getContentPane().add(lblResultOfAverage);
		
		btnHtml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				ScoreIO.initialize().inputAndProcessScore();
				lblResultOfAverage.setText(Double.toString(ScoreIO.average));
				lblResultOfGpa.setText(Double.toString(ScoreIO.GPA));
				btnXls.setEnabled(true);
				btnHtml.setEnabled(false);
				JOptionPane.showConfirmDialog(null, "html读取成功，成绩已经处理", "进度", JOptionPane.PLAIN_MESSAGE);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "发生异常", "错误",JOptionPane.ERROR_MESSAGE);  
				e2.printStackTrace();
			}
				
				
			}
		});
		
		btnXls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ScoreIO.initialize().outputXls();
					JOptionPane.showConfirmDialog(null, "成绩已经保存到outpuutScore.xls，请到程序根目录查看", "进度", JOptionPane.PLAIN_MESSAGE);
				} catch (Exception e2) {
			
					JOptionPane.showMessageDialog(null, "发生异常", "错误",JOptionPane.ERROR_MESSAGE);  
					e2.printStackTrace();
				}
			}
		});
	}
}
