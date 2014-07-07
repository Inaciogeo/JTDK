package br.org.funcate.jtdk.style.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import br.org.funcate.jtdk.util.ImageIconLoader;

@SuppressWarnings("serial")
public class PointStyleView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSize;
	private JTextField txtFile;
	private JTextField txtHeight;
	private JTextField txtDisplacementX;
	private JTextField txtDisplacementY;
	private JTable tblVisual;
	private JLabel lblStyleName;
	private JTabbedPane tpnlConfigPoint;
	private JPanel pnlDisplacement;
	private JPanel pnlVisual;
	private JPanel pnlPreview;
	private JPanel pnlDrawPreview;
	private JScrollPane scrollVisualPane;
	private JButton btnAdd;
	private JButton btnUpArrow;
	private JButton btnDownArrow;
	private JButton btnDeleteVisual;
	private JButton btnCopyVisual;
	private JLabel lblDisplacementX;
	private JLabel lblDisplacementY;
	private JPanel pnlMark;
	private JButton btnColor;
	private JPanel pnlColor;
	private JLabel lblSize;
	private JLabel lblType;
	private JPanel pnlImage;
	private JComboBox cboType;
	private JButton btnFile;
	private JLabel lblHeight;
	private JLabel lblTransparency;
	private JComboBox cboTransparency;
	private JLabel lblAngle;
	private JButton btnSave;
	private JButton btnCancel;
	private JTextField txtStyleName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
		} catch (InstantiationException ex) {
		} catch (IllegalAccessException ex) {
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
		}

		try {
			PointStyleView dialog = new PointStyleView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PointStyleView() {
		setTitle("Estilo de Pontos");
		setBounds(100, 100, 468, 590);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblStyleName = new JLabel("Nome do Estilo:");

		txtStyleName = new JTextField();
		txtStyleName.setColumns(10);

		tpnlConfigPoint = new JTabbedPane(JTabbedPane.TOP);
		tpnlConfigPoint.setBorder(new LineBorder(new Color(0, 0, 0)));

		pnlDisplacement = new JPanel();
		pnlDisplacement.setBorder(new TitledBorder(null, "Deslocamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		pnlVisual = new JPanel();
		pnlVisual.setBorder(new TitledBorder(null, "Visuais", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		pnlPreview = new JPanel();
		pnlPreview.setBorder(new TitledBorder(null, "Pr\u00E9-visualiza\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		pnlDrawPreview = new JPanel();
		pnlDrawPreview.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		pnlDrawPreview.setBackground(Color.WHITE);
		pnlDrawPreview.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		GroupLayout gl_pnlPreview = new GroupLayout(pnlPreview);
		gl_pnlPreview.setHorizontalGroup(gl_pnlPreview.createParallelGroup(Alignment.TRAILING).addGroup(
				Alignment.LEADING,
				gl_pnlPreview.createSequentialGroup().addGap(27)
						.addComponent(pnlDrawPreview, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(32, Short.MAX_VALUE)));

		gl_pnlPreview.setVerticalGroup(gl_pnlPreview.createParallelGroup(Alignment.LEADING).addGroup(
				gl_pnlPreview.createSequentialGroup().addComponent(pnlDrawPreview, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
						.addContainerGap()));

		pnlPreview.setLayout(gl_pnlPreview);

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_contentPanel
						.createSequentialGroup()
						.addGroup(
								gl_contentPanel
										.createParallelGroup(Alignment.LEADING)
										.addGroup(
												gl_contentPanel
														.createSequentialGroup()
														.addContainerGap()
														.addGroup(
																gl_contentPanel
																		.createParallelGroup(Alignment.LEADING)
																		.addGroup(
																				gl_contentPanel
																						.createSequentialGroup()
																						.addComponent(lblStyleName,
																								GroupLayout.PREFERRED_SIZE, 91,
																								GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(ComponentPlacement.RELATED)
																						.addComponent(txtStyleName,
																								GroupLayout.PREFERRED_SIZE, 312,
																								GroupLayout.PREFERRED_SIZE))
																		.addComponent(pnlDisplacement, GroupLayout.DEFAULT_SIZE, 412,
																				Short.MAX_VALUE)
																		.addGroup(
																				gl_contentPanel
																						.createSequentialGroup()
																						.addComponent(pnlVisual,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(ComponentPlacement.RELATED,
																								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																						.addComponent(pnlPreview,
																								GroupLayout.PREFERRED_SIZE, 214,
																								GroupLayout.PREFERRED_SIZE))))
										.addGroup(
												gl_contentPanel.createSequentialGroup().addGap(12)
														.addComponent(tpnlConfigPoint, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)))
						.addContainerGap()));

		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_contentPanel
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblStyleName, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtStyleName, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(tpnlConfigPoint, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnlDisplacement, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(
								gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(pnlVisual, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
										.addComponent(pnlPreview, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))));

		scrollVisualPane = new JScrollPane();

		btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnAdd.setIcon(ImageIconLoader.createImageIcon("br/org/funcate/jtdk/style/img/add.gif", PointStyleView.class));

		btnUpArrow = new JButton("");
		btnUpArrow.setIcon(ImageIconLoader.createImageIcon("br/org/funcate/jtdk/style/img/upArrow.png", PointStyleView.class));

		btnDownArrow = new JButton("");
		btnDownArrow.setIcon(ImageIconLoader.createImageIcon("br/org/funcate/jtdk/style/img/downArrow.png", PointStyleView.class));

		btnDeleteVisual = new JButton("");
		btnDeleteVisual.setIcon(ImageIconLoader.createImageIcon("br/org/funcate/jtdk/style/img/delete.gif", PointStyleView.class));

		btnCopyVisual = new JButton("");
		btnCopyVisual.setIcon(ImageIconLoader.createImageIcon("br/org/funcate/jtdk/style/img/copy.gif", PointStyleView.class));

		GroupLayout gl_pnlVisual = new GroupLayout(pnlVisual);

		gl_pnlVisual.setHorizontalGroup(gl_pnlVisual.createParallelGroup(Alignment.TRAILING).addGroup(
				gl_pnlVisual
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_pnlVisual
										.createParallelGroup(Alignment.LEADING)
										.addGroup(
												gl_pnlVisual.createParallelGroup(Alignment.TRAILING).addComponent(btnUpArrow)
														.addComponent(btnAdd)).addComponent(btnDownArrow).addComponent(btnDeleteVisual)
										.addComponent(btnCopyVisual))
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollVisualPane, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)));

		gl_pnlVisual.setVerticalGroup(gl_pnlVisual.createParallelGroup(Alignment.LEADING).addGroup(
				gl_pnlVisual
						.createSequentialGroup()
						.addGroup(
								gl_pnlVisual
										.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollVisualPane, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
										.addGroup(
												gl_pnlVisual
														.createSequentialGroup()
														.addContainerGap()
														.addComponent(btnAdd)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnUpArrow)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnDownArrow)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnDeleteVisual)
														.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE).addComponent(btnCopyVisual)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		tblVisual = new JTable();
		tblVisual.setModel(new DefaultTableModel(new Object[][] { { null }, }, new String[] { "Visuais" }));
		scrollVisualPane.setViewportView(tblVisual);
		pnlVisual.setLayout(gl_pnlVisual);

		lblDisplacementX = new JLabel("X (mm):");

		txtDisplacementX = new JTextField();
		txtDisplacementX.setText("0.00");
		txtDisplacementX.setColumns(10);

		lblDisplacementY = new JLabel("Y (mm):");

		txtDisplacementY = new JTextField();
		txtDisplacementY.setText("0.00");
		txtDisplacementY.setColumns(10);

		GroupLayout gl_pnlDisplacement = new GroupLayout(pnlDisplacement);

		gl_pnlDisplacement.setHorizontalGroup(gl_pnlDisplacement.createParallelGroup(Alignment.LEADING).addGroup(
				gl_pnlDisplacement.createSequentialGroup().addContainerGap().addComponent(lblDisplacementX)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtDisplacementX, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE).addGap(12)
						.addComponent(lblDisplacementY).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtDisplacementY, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(9, Short.MAX_VALUE)));

		gl_pnlDisplacement.setVerticalGroup(gl_pnlDisplacement.createParallelGroup(Alignment.LEADING).addGroup(
				gl_pnlDisplacement
						.createSequentialGroup()
						.addGroup(
								gl_pnlDisplacement
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblDisplacementX)
										.addComponent(txtDisplacementX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtDisplacementY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE).addComponent(lblDisplacementY))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pnlDisplacement.setLayout(gl_pnlDisplacement);

		pnlMark = new JPanel();
		tpnlConfigPoint.addTab("Marca", null, pnlMark, null);

		btnColor = new JButton("Cor...");

		pnlColor = new JPanel();
		pnlColor.setBackground(Color.YELLOW);
		pnlColor.setBorder(new LineBorder(new Color(0, 0, 0)));

		lblSize = new JLabel("Tamanho (mm):");

		txtSize = new JTextField();
		txtSize.setText("10.00");
		txtSize.setColumns(10);

		lblType = new JLabel("Tipo:");

		cboType = new JComboBox();
		GroupLayout gl_pnlMark = new GroupLayout(pnlMark);
		gl_pnlMark.setHorizontalGroup(gl_pnlMark.createParallelGroup(Alignment.LEADING).addGroup(
				gl_pnlMark
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_pnlMark.createParallelGroup(Alignment.TRAILING).addComponent(lblType).addComponent(btnColor)
										.addComponent(lblSize))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlMark.createParallelGroup(Alignment.LEADING)
										.addComponent(txtSize, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
										.addComponent(pnlColor, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
										.addComponent(cboType, 0, 300, Short.MAX_VALUE)).addContainerGap()));
		gl_pnlMark.setVerticalGroup(gl_pnlMark.createParallelGroup(Alignment.LEADING).addGroup(
				gl_pnlMark
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_pnlMark
										.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(pnlColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(btnColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlMark
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE).addComponent(lblSize))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlMark
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblType)
										.addComponent(cboType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)).addContainerGap(49, Short.MAX_VALUE)));
		pnlMark.setLayout(gl_pnlMark);

		pnlImage = new JPanel();
		tpnlConfigPoint.addTab("Imagem", null, pnlImage, null);

		txtFile = new JTextField();
		txtFile.setEnabled(false);
		txtFile.setColumns(10);

		btnFile = new JButton("Arquivo...");

		lblHeight = new JLabel("Altura (mm):");

		txtHeight = new JTextField();
		txtHeight.setColumns(10);

		lblTransparency = new JLabel("Transpar\u00EAncia (%):");

		cboTransparency = new JComboBox();

		lblAngle = new JLabel("\u00C2ngulo:");

		JSpinner spnAngle = new JSpinner();
		GroupLayout gl_pnlImage = new GroupLayout(pnlImage);
		gl_pnlImage.setHorizontalGroup(gl_pnlImage.createParallelGroup(Alignment.LEADING).addGroup(
				gl_pnlImage
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_pnlImage
										.createParallelGroup(Alignment.LEADING)
										.addGroup(
												gl_pnlImage.createSequentialGroup()
														.addComponent(txtFile, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnFile))
										.addGroup(
												gl_pnlImage
														.createSequentialGroup()
														.addGap(6)
														.addGroup(
																gl_pnlImage
																		.createParallelGroup(Alignment.LEADING)
																		.addGroup(
																				gl_pnlImage
																						.createSequentialGroup()
																						.addGap(36)
																						.addComponent(lblHeight)
																						.addPreferredGap(ComponentPlacement.RELATED)
																						.addComponent(txtHeight, GroupLayout.DEFAULT_SIZE,
																								272, Short.MAX_VALUE))
																		.addGroup(
																				gl_pnlImage
																						.createSequentialGroup()
																						.addGroup(
																								gl_pnlImage
																										.createParallelGroup(
																												Alignment.TRAILING)
																										.addComponent(lblAngle)
																										.addComponent(lblTransparency))
																						.addPreferredGap(ComponentPlacement.RELATED)
																						.addGroup(
																								gl_pnlImage
																										.createParallelGroup(
																												Alignment.LEADING, false)
																										.addComponent(spnAngle)
																										.addComponent(cboTransparency, 0,
																												61, Short.MAX_VALUE))))))
						.addGap(14)));
		gl_pnlImage.setVerticalGroup(gl_pnlImage.createParallelGroup(Alignment.LEADING).addGroup(
				gl_pnlImage
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_pnlImage
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE).addComponent(btnFile))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlImage
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE).addComponent(lblHeight))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlImage
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblTransparency)
										.addComponent(cboTransparency, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_pnlImage
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblAngle)
										.addComponent(spnAngle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)).addContainerGap(17, Short.MAX_VALUE)));
		pnlImage.setLayout(gl_pnlImage);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSave = new JButton("Salvar");
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				btnCancel = new JButton("Cancelar");
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}

	/**
	 * @return the txtSize
	 */
	public JTextField getTxtSize() {
		return txtSize;
	}

	/**
	 * @param txtSize
	 *            the txtSize to set
	 */
	public void setTxtSize(JTextField txtSize) {
		this.txtSize = txtSize;
	}

	/**
	 * @return the txtFile
	 */
	public JTextField getTxtFile() {
		return txtFile;
	}

	/**
	 * @param txtFile
	 *            the txtFile to set
	 */
	public void setTxtFile(JTextField txtFile) {
		this.txtFile = txtFile;
	}

	/**
	 * @return the txtHeight
	 */
	public JTextField getTxtHeight() {
		return txtHeight;
	}

	/**
	 * @param txtHeight
	 *            the txtHeight to set
	 */
	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	/**
	 * @return the txtDisplacementX
	 */
	public JTextField getTxtDisplacementX() {
		return txtDisplacementX;
	}

	/**
	 * @param txtDisplacementX
	 *            the txtDisplacementX to set
	 */
	public void setTxtDisplacementX(JTextField txtDisplacementX) {
		this.txtDisplacementX = txtDisplacementX;
	}

	/**
	 * @return the txtDisplacementY
	 */
	public JTextField getTxtDisplacementY() {
		return txtDisplacementY;
	}

	/**
	 * @param txtDisplacementY
	 *            the txtDisplacementY to set
	 */
	public void setTxtDisplacementY(JTextField txtDisplacementY) {
		this.txtDisplacementY = txtDisplacementY;
	}

	/**
	 * @return the pnlDrawPreview
	 */
	public JPanel getPnlDrawPreview() {
		return pnlDrawPreview;
	}

	/**
	 * @param pnlDrawPreview
	 *            the pnlDrawPreview to set
	 */
	public void setPnlDrawPreview(JPanel pnlDrawPreview) {
		this.pnlDrawPreview = pnlDrawPreview;
	}

	/**
	 * @return the btnAdd
	 */
	public JButton getBtnAdd() {
		return btnAdd;
	}

	/**
	 * @param btnAdd
	 *            the btnAdd to set
	 */
	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	/**
	 * @return the btnUpArrow
	 */
	public JButton getBtnUpArrow() {
		return btnUpArrow;
	}

	/**
	 * @param btnUpArrow
	 *            the btnUpArrow to set
	 */
	public void setBtnUpArrow(JButton btnUpArrow) {
		this.btnUpArrow = btnUpArrow;
	}

	/**
	 * @return the btnDownArrow
	 */
	public JButton getBtnDownArrow() {
		return btnDownArrow;
	}

	/**
	 * @param btnDownArrow
	 *            the btnDownArrow to set
	 */
	public void setBtnDownArrow(JButton btnDownArrow) {
		this.btnDownArrow = btnDownArrow;
	}

	/**
	 * @return the btnDeleteVisual
	 */
	public JButton getBtnDeleteVisual() {
		return btnDeleteVisual;
	}

	/**
	 * @param btnDeleteVisual
	 *            the btnDeleteVisual to set
	 */
	public void setBtnDeleteVisual(JButton btnDeleteVisual) {
		this.btnDeleteVisual = btnDeleteVisual;
	}

	/**
	 * @return the btnCopyVisual
	 */
	public JButton getBtnCopyVisual() {
		return btnCopyVisual;
	}

	/**
	 * @param btnCopyVisual
	 *            the btnCopyVisual to set
	 */
	public void setBtnCopyVisual(JButton btnCopyVisual) {
		this.btnCopyVisual = btnCopyVisual;
	}

	/**
	 * @return the btnColor
	 */
	public JButton getBtnColor() {
		return btnColor;
	}

	/**
	 * @param btnColor
	 *            the btnColor to set
	 */
	public void setBtnColor(JButton btnColor) {
		this.btnColor = btnColor;
	}

	/**
	 * @return the pnlColor
	 */
	public JPanel getPnlColor() {
		return pnlColor;
	}

	/**
	 * @param pnlColor
	 *            the pnlColor to set
	 */
	public void setPnlColor(JPanel pnlColor) {
		this.pnlColor = pnlColor;
	}

	/**
	 * @return the cboType
	 */
	public JComboBox getCboType() {
		return cboType;
	}

	/**
	 * @param cboType
	 *            the cboType to set
	 */
	public void setCboType(JComboBox cboType) {
		this.cboType = cboType;
	}

	/**
	 * @return the btnFile
	 */
	public JButton getBtnFile() {
		return btnFile;
	}

	/**
	 * @param btnFile
	 *            the btnFile to set
	 */
	public void setBtnFile(JButton btnFile) {
		this.btnFile = btnFile;
	}

	/**
	 * @return the cboTransparency
	 */
	public JComboBox getCboTransparency() {
		return cboTransparency;
	}

	/**
	 * @param cboTransparency
	 *            the cboTransparency to set
	 */
	public void setCboTransparency(JComboBox cboTransparency) {
		this.cboTransparency = cboTransparency;
	}

	/**
	 * @return the btnSave
	 */
	public JButton getBtnSave() {
		return btnSave;
	}

	/**
	 * @param btnSave
	 *            the btnSave to set
	 */
	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	/**
	 * @return the btnCancel
	 */
	public JButton getBtnCancel() {
		return btnCancel;
	}

	/**
	 * @param btnCancel
	 *            the btnCancel to set
	 */
	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	/**
	 * @return the txtStyleName
	 */
	public JTextField getTxtStyleName() {
		return txtStyleName;
	}

	/**
	 * @param txtStyleName
	 *            the txtStyleName to set
	 */
	public void setTxtStyleName(JTextField txtStyleName) {
		this.txtStyleName = txtStyleName;
	}

	/**
	 * @return the tblVisual
	 */
	public JTable getTblVisual() {
		return tblVisual;
	}

	/**
	 * @param tblVisual
	 *            the tblVisual to set
	 */
	public void setTblVisual(JTable tblVisual) {
		this.tblVisual = tblVisual;
	}

}
