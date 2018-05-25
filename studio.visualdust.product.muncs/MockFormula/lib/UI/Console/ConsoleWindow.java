package MockFormula.lib.UI.Console;

import MockFormula.Database.DataResource;
import MockFormula.lib.DataStructure.Resource;
import MockFormula.lib.DataStructure.VersionView;
import MockFormula.lib.MFLauncher;
import MockFormula.lib.Methord.*;
import MockFormula.lib.UI.Display.ScreenWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class ConsoleWindow {
    public JFrame consolewind = new JFrame();
    public Resource respack = new Resource();
    public Clockcontrol clocker = new Clockcontrol();
    public ScreenWindow screen = new ScreenWindow();//Create the screen window
    public DisplayConfigWindow configwind = new DisplayConfigWindow();

    public JPanel buttonFrame = new JPanel();
    public JPanel border = new JPanel();
    public JPanel subframe_left = new JPanel();
    public JPanel subframe_right = new JPanel();
    public JPanel listpanel = new JPanel();
    public JPanel screenactionpanel = new JPanel();
    public JPanel screentwiterpanel = new JPanel();
    public JPanel screenviewpanel = new JPanel();
    public ThumbPanel screennowpanel = new ThumbPanel();
    public JPanel dividerpanel = new JPanel();
    public JPanel temppanel = new JPanel();
    public JPanel infofiller = new JPanel();
    public JPanel infoconsolepanel = new JPanel();
    public JPanel questionpanel = new JPanel();
    public JPanel clockcontrolpanel = new JPanel();
    public JPanel clockpanel = new JPanel();
    public JTextArea screennexttextarea = new JTextArea();
    public JScrollPane screennexttextscrollpane = new JScrollPane(screennexttextarea);
    public JTextArea about = new JTextArea();
    public JTextField twitertextfield = new JTextField();
    public JTextField announcernametextfield = new JTextField();
    public JSpinner totaltimefield = new JSpinner();//number only
    public JSpinner singletimefield = new JSpinner();//number only
    public JPanel clockfieldpanel = new JPanel();
    public JTextField clocktotaltextfield = new JTextField("0");
    public JTextField clocksingletextfield = new JTextField("0");
    public JTextField questionfield = new JTextField();
    public JTextArea recordtextarea = new JTextArea("");
    public JButton infofilleryesbutton = new JButton("确认");
    public JButton questionyesbutton = new JButton("确认");
    public List listcontrol = new List();
    public VersionView versionView = new VersionView();
    public JTextArea noticeanderrortextarea = new JTextArea();

    public JComboBox infotype = new JComboBox();
    public JComboBox questiontype = new JComboBox();
    public JComboBox order = new JComboBox();

    public JButton startclockbutton = new JButton("开始计时");
    public JButton pauseclockbutton = new JButton("暂停计时");
    public JButton nexttimebutton = new JButton("结束此次");
    public JButton clearclockbutton = new JButton("清空计时器");
    public JButton twitbutton = new JButton("推送到主屏");

    public int totaltime;
    public int singletime;

    JFileChooser recordsavesfilechooser = new JFileChooser();

    public class EmptyImageObserver implements ImageObserver {

        @Override
        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
            return false;
        }
    }

    public class ThumbPanel extends JPanel {
        private Image image;

        public void displayImage(Image img) {
            this.image = Objects.requireNonNull(img);
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.scale((double) this.getWidth() / image.getWidth(this), (double) this.getHeight() / image.getHeight(this));
                g.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), this);
            }
        }
    }

    public ConsoleWindow() {

        consolewind.setLayout(new GridLayout(1, 2));
        consolewind.setTitle("MockFormula 2018.6 By Mr.GZT");
        consolewind.setSize(111, 111);
        consolewind.setLocationRelativeTo(null);
        consolewind.setExtendedState(JFrame.MAXIMIZED_BOTH);
        consolewind.setBackground(respack.getcolor("backgroundcolor"));

        screen.screenwindow.setMinimumSize(new Dimension(800, 600));
        screen.border.setBackground(new Color(0, 36, 78));

        resetinfofieldscolor();
        resetinfotype();
        resetquestiontype();

        consolewind.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        consolewind.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Exceptionoutput.outputEvent("WINDOW_CLOSE_CLICKED");
                if (MessageWindow.showMessageWindow(2, "帅气的主席团，你真的能狠下心退出MUNCSLemon吗？").equals("0")) {
                    Exceptionoutput.outputException(new Exception("MUNCSLemon_stop"));
                    System.exit(0);
                }
            }
        });
        ScreenWindow.screenwindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        ScreenWindow.screenwindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Exceptionoutput.outputEvent("WINDOW_CLOSE_CLICKED");
                if (MessageWindow.showMessageWindow(2, "帅气的主席团，你真的能狠下心退出MUNCSLemon吗？").equals("0")) {
                    Exceptionoutput.outputException(new Exception("MUNCSLemon_stop"));
                    System.exit(0);
                }
            }
        });

        JPopupMenu errinfopopmenu = new JPopupMenu();
        JCheckBoxMenuItem erreditablemenuitem = new JCheckBoxMenuItem("防误触");
        erreditablemenuitem.setSelected(true);
        JMenuItem abouttheerrormenuitem = new JMenuItem("关于错误...");
        errinfopopmenu.add(erreditablemenuitem);
        errinfopopmenu.add(abouttheerrormenuitem);
        errinfopopmenu.add(new JMenuItem("取消"));
        noticeanderrortextarea.add(errinfopopmenu);
        noticeanderrortextarea.setEditable(false);
        noticeanderrortextarea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    Exceptionoutput.outputEvent("PopupTrigger at notice_and_error_textarea");
                    errinfopopmenu.show(noticeanderrortextarea, e.getX(), e.getY());
                }
            }
        });
        erreditablemenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (erreditablemenuitem.isSelected()) {
                    noticeanderrortextarea.setEditable(false);
                } else {
                    noticeanderrortextarea.setEditable(true);
                }
            }
        });
        abouttheerrormenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageWindow.showMessageWindow(0, versionView.getAbouterror());
            }
        });

        try {
            screen.screenwindow.setIconImage(new ImageIcon(DataResource.class.getResource("ICONIMG.png").toURI().toURL()).getImage());
        } catch (MalformedURLException | URISyntaxException e) {
            Exceptionoutput.outputException(e);
        }

        //recordsavesfilechooser.setSelectedFile(null);
        recordsavesfilechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        recordsavesfilechooser.setDialogTitle("文件后缀名请自行添加");

        about.setLineWrap(true);
        about.setWrapStyleWord(true);

        recordtextarea.setBackground(respack.getcolor("backgroundcolor"));
        recordtextarea.setForeground(respack.getcolor("defaultfontcolor"));
        recordtextarea.setFont(respack.getFont("recordtextareafont"));
        recordtextarea.setLineWrap(true);
        recordtextarea.setEditable(false);

        JScrollPane recordtextScroll = new JScrollPane(recordtextarea);
        buttonFrame.setBackground(respack.getcolor("backgroundcolor"));
        buttonFrame.setLayout(TempCreater.tempgridlayout(2, 1, 10, 10));

        consolewind.setLayout(TempCreater.tempgridlayout(1, 1, 0, 10));

        subframe_left.setLayout(TempCreater.tempgridlayout(7, 1, 10, 10));
        subframe_right.setLayout(TempCreater.tempgridlayout(2, 1, 10, 10));
        subframe_left.setBackground(respack.getcolor("backgroundcolor"));
        subframe_right.setBackground(respack.getcolor("backgroundcolor"));
        border.setBorder(BorderFactory.createLineBorder(respack.getcolor("backgroundcolor"), 20));
        border.setBackground(respack.getcolor("backgroundcolor"));
        border.setLayout(TempCreater.tempgridlayout(1, 2, 15, 15));
        border.add(subframe_left);
        listpanel.setBackground(respack.getcolor("backgroundcolor"));
        listpanel.setLayout(TempCreater.tempgridlayout(1, 2, 0, 0));

        border.add(subframe_right);
        subframe_right.add(recordtextScroll);
        subframe_right.add(listpanel);
        consolewind.add(border);

        screentwiterpanel.setBackground(respack.getcolor("backgroundcolor"));
        screentwiterpanel.setLayout(TempCreater.tempgridlayout(2, 1, 5, 5));
        twitertextfield.setBackground(respack.getcolor("backgroundcolor"));
        twitertextfield.setFont(respack.getFont("defaultlabelfont"));
        twitertextfield.setForeground(respack.getcolor("textfielddefaultbuckcolor"));
        twitertextfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (KeyEvent.getKeyText(e.getKeyCode()).equals("Enter")) {
                    Exceptionoutput.outputEvent("twiter_text_field Keyboard pressed ENTER");
                    if (!twitertextfield.getText().equals("")) {
                        twitclick();
                    }
                }
            }
        });
        JScrollPane twiterscrollpane = new JScrollPane(twitertextfield);
        screentwiterpanel.add(twiterscrollpane);
        screentwiterpanel.add(twitbutton);
        twitbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exceptionoutput.outputEvent("twit_button clicked");
                if (!twitertextfield.getText().equals("")) {
                    twitclick();
                }
            }
        });

        screenactionpanel.setBackground(respack.getcolor("backgroundcolor"));
        screenactionpanel.setLayout(TempCreater.tempgridlayout(1, 2, 5, 5));
        screenviewpanel.setLayout(TempCreater.tempgridlayout(1, 2, 5, 5));
        screenviewpanel.setBackground(respack.getcolor("backgroundcolor"));
        JPopupMenu screennextpopmenu = new JPopupMenu();
        JMenuItem clearnextmenuitem = new JMenuItem("取消这些更新");
        screennextpopmenu.add(clearnextmenuitem);
        clearnextmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exceptionoutput.outputEvent("clear_next_menu_item");
                if (MessageWindow.showMessageWindow(2, "确定要取消这些更新?").equals("0")) {
                    screennexttextarea.setText("");
                    clearandresetall();
                }
            }
        });
        screennextpopmenu.add(clearnextmenuitem);
        screennextpopmenu.add(new JMenuItem("取消"));
        screennexttextarea.setLineWrap(true);
        screennexttextarea.setEditable(false);
        screennexttextarea.setFont(new Font("微软雅黑", 0, 12));
        screennexttextarea.setForeground(respack.getcolor("textfielddefaultbuckcolor"));
        screennexttextarea.setBackground(respack.getcolor("backgroundcolor"));
        screennexttextarea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    Exceptionoutput.outputEvent("PopupTrigger at screen_next_text_area");
                    screennextpopmenu.show(screennexttextarea, e.getX(), e.getY());
                }
            }
        });

        screennowpanel.setBackground(new Color(111, 111, 111));
        JPopupMenu screencontrolpopmenu = new JPopupMenu();
        JMenuItem openscreenmenuitem = new JMenuItem("打开屏幕");
        JMenuItem refreshscreenmenuitem = new JMenuItem("刷新屏幕");

        JMenu infoactionmenu = new JMenu("动议操作...");
        JMenuItem infoacceptmenuitem = new JMenuItem("通过屏幕上的动议");
        JMenuItem infocancelmenuitem = new JMenuItem("否决屏幕上的动议");
        JMenuItem infoclearmenuitem = new JMenuItem("清空屏幕上的动议");
        infoactionmenu.add(infoacceptmenuitem);
        infoactionmenu.add(infocancelmenuitem);
        infoactionmenu.add(infoclearmenuitem);
        infoacceptmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenWindow.infoaction(0);
                recordtextarea.append(LocalTime.now().toString() + "：屏幕上的动议获得通过\r\r\n\n");
            }
        });
        infocancelmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenWindow.infoaction(1);
                recordtextarea.append(LocalTime.now().toString() + "：屏幕上的动议未获得通过\r\r\n\n");
            }
        });
        infoclearmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenWindow.infoaction(2);
            }
        });

        JMenu questionactionmenu = new JMenu("问题操作...");
        JMenuItem questionacceptmenuitem = new JMenuItem("通过屏幕上的问题");
        JMenuItem questioncancelmenuitem = new JMenuItem("否决屏幕上的问题");
        JMenuItem questionclearmenuitem = new JMenuItem("清空屏幕上的问题");
        questionactionmenu.add(questionacceptmenuitem);
        questionactionmenu.add(questioncancelmenuitem);
        questionactionmenu.add(questionclearmenuitem);
        questionacceptmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenWindow.questionaction(0);
            }
        });
        questioncancelmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenWindow.questionaction(1);
            }
        });
        questionclearmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenWindow.questionaction(2);
            }
        });

        JMenuItem closescreenmenuitem = new JMenuItem("关闭屏幕");
        JMenuItem displaysettingmenuitem = new JMenuItem("显示设置");
        displaysettingmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configwind.displayConfigFrame.setLocation(consolewind.getX() + consolewind.getWidth() / 4, consolewind.getY() + consolewind.getHeight() / 4);
                configwind.setvis(true);
                Exceptionoutput.outputEvent("Config_Wind show");
            }
        });
        JMenuItem voiceControlMenuItem = new JCheckBoxMenuItem("朗读设置");//TODO Finish it .

        screencontrolpopmenu.add(openscreenmenuitem);
        screencontrolpopmenu.add(refreshscreenmenuitem);
        screencontrolpopmenu.add(infoactionmenu);
        screencontrolpopmenu.add(questionactionmenu);
        screencontrolpopmenu.add(closescreenmenuitem);
        screencontrolpopmenu.add(displaysettingmenuitem);
        screencontrolpopmenu.add(new JMenuItem("取消"));
        closescreenmenuitem.setEnabled(false);
        screennowpanel.add(screencontrolpopmenu);
        screennowpanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    Exceptionoutput.outputEvent("PopupTrigger at Screen_now_panel");
                    screencontrolpopmenu.show(screennowpanel, e.getX(), e.getY());
                }
            }
        });
        screenviewpanel.add(screennowpanel);
        screennexttextscrollpane.setBackground(respack.getcolor("backgroundcolor"));
        screenviewpanel.add(screennexttextscrollpane);
        screenactionpanel.add(screenviewpanel);
        screenactionpanel.add(screentwiterpanel);
        subframe_left.add(screenactionpanel);


        JPopupMenu recordtextareapopmenu = new JPopupMenu();
        JMenuItem recordsaverecordmenuitem = new JMenuItem("保存");
        JMenuItem recordresaverecordmenuitem = new JMenuItem("另存为");
//        JMenuItem printrecordmenuitem = new JMenuItem("将记录输出至屏幕");
        JMenuItem recordclearmenuitem = new JMenuItem("清除记录");
        JCheckBoxMenuItem recordeditablemenuitem = new JCheckBoxMenuItem("防误触");
        JMenuItem editableintromenuitem = new JMenuItem("什么是防误触？");
        recordeditablemenuitem.setSelected(true);
        JMenu recordfileoperator = new JMenu("文件...");
        JMenu recordeditablemenu = new JMenu("防误触...");
        recordeditablemenu.add(recordeditablemenuitem);
        recordeditablemenu.add(editableintromenuitem);
        recordfileoperator.add(recordsaverecordmenuitem);
        recordfileoperator.add(recordresaverecordmenuitem);
        recordtextareapopmenu.add(recordeditablemenu);
        recordtextareapopmenu.add(recordfileoperator);
//        recordtextareapopmenu.add(printrecordmenuitem);
        recordtextareapopmenu.add(recordclearmenuitem);
        recordtextareapopmenu.add(new JMenuItem("取消"));
        recordtextarea.add(recordtextareapopmenu);
        recordtextarea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    Exceptionoutput.outputEvent("PopupTrigger at Record_text_area");
                    recordtextareapopmenu.show(recordtextarea, e.getX(), e.getY());
                }
            }
        });
        recordclearmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MessageWindow.showMessageWindow(2, "是否要清除控制窗口中的临时记录？").equals("0")) {
                    if (MessageWindow.showMessageWindow(2, "请再次确认").equals("0"))
                        recordtextarea.setText("");
                }
            }
        });
        recordsaverecordmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (recordsavesfilechooser.getSelectedFile() == null) {
                        recordsavesfilechooser.setLocation(DisplayConfigWindow.displayConfigFrame.getX(), DisplayConfigWindow.displayConfigFrame.getY());
                        recordsavesfilechooser.showSaveDialog(consolewind);
                        Exceptionoutput.outputEvent("Save_Dialog show");
                    }
                    File saves = recordsavesfilechooser.getSelectedFile();
                    OutputStream recordoutputstream = new FileOutputStream(saves, false);
                    recordoutputstream.write((LocalDateTime.now() + "保存的记录\r\n").getBytes());
                    recordoutputstream.write((recordtextarea.getText() + "\r\n").getBytes());
                    TempCreater.tempnoticePopmenu("文件已写入" + recordsavesfilechooser.getSelectedFile().toString(), 10000).show(subframe_right, recordtextarea.getX() + 50, recordtextarea.getY() + recordtextarea.getHeight());
                } catch (Exception e1) {
                    Exceptionoutput.outputException(e1);
                    TempCreater.tempnoticePopmenu("文件没有保存,原因是：" + e1.toString() + "，试试看另存为", 5000).show(subframe_right, recordtextarea.getX() + 50, recordtextarea.getY() + recordtextarea.getHeight());
                }
            }
        });
        recordresaverecordmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    recordsavesfilechooser.showSaveDialog(consolewind);
                    Exceptionoutput.outputEvent("Save_Dialog show");
                    File saves = recordsavesfilechooser.getSelectedFile();
                    OutputStream recordoutputstream = new FileOutputStream(saves, true);
                    recordoutputstream.write((LocalDateTime.now() + "保存的记录\r\n").getBytes());
                    recordoutputstream.write((recordtextarea.getText() + "\r\n").getBytes());
                    TempCreater.tempnoticePopmenu("文件已写入" + recordsavesfilechooser.getSelectedFile().toString(), 10000).show(subframe_right, recordtextarea.getX() + 50, recordtextarea.getY() + recordtextarea.getHeight());
                } catch (Exception e1) {
                    Exceptionoutput.outputException(e1);
                    TempCreater.tempnoticePopmenu("文件没有保存,原因是：" + e1.toString() + "，试试看另存为", 5000).show(subframe_right, recordtextarea.getX() + 50, recordtextarea.getY() + recordtextarea.getHeight());
                }
            }
        });
        recordeditablemenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (recordeditablemenuitem.isSelected()) {
                    recordtextarea.setEditable(false);
                } else {
                    recordtextarea.setEditable(true);
                }
            }
        });
        editableintromenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageWindow.showMessageWindow(0, versionView.getAbouteditable());
            }
        });

        dividerpanel.setBackground(respack.getcolor("backgroundcolor"));
        dividerpanel.setLayout(TempCreater.tempgridlayout(2, 2, 5, 5));
        temppanel.setBackground(respack.getcolor("backgroundcolor"));
        JLabel discribe_1 = new JLabel(" 进行新的动议，内容为：");
        discribe_1.setFont(new Font("微软雅黑", 0, 20));
        discribe_1.setForeground(respack.getcolor("titlefontcolor"));
        dividerpanel.add(discribe_1);
        dividerpanel.add(TempCreater.templabel("", respack.getFont("defaulttextfont"), respack.getcolor("backgroundcolor"), respack.getcolor("titlefontcolor")));
        dividerpanel.add(about);
        dividerpanel.add(infotype);
        subframe_left.add(dividerpanel);

        infofiller.setBackground(respack.getcolor("backgroundcolor"));
        infofiller.setLayout(TempCreater.tempgridlayout(3, 2, 5, 5));
        Font labelfont = respack.getFont("defaultlabelfont");
        infofiller.add(TempCreater.templabel(" 提出方名称", labelfont, respack.getcolor("backgroundcolor"), respack.getcolor("titlefontcolor")));
        infofiller.add(announcernametextfield);
        infofiller.add(TempCreater.templabel(" 总时长", labelfont, respack.getcolor("backgroundcolor"), respack.getcolor("titlefontcolor")));
        infofiller.add(totaltimefield);
        infofiller.add(TempCreater.templabel(" 分时长", labelfont, respack.getcolor("backgroundcolor"), respack.getcolor("titlefontcolor")));
        infofiller.add(singletimefield);
        subframe_left.add(infofiller);

        infoconsolepanel.setLayout(TempCreater.tempgridlayout(2, 3, 10, 10));
        infoconsolepanel.setBackground(respack.getcolor("backgroundcolor"));
        JButton infofillerclearbutton = new JButton("重置以上内容");
        infoconsolepanel.add(order);
        infoconsolepanel.add(infofillerclearbutton);
        infoconsolepanel.add(infofilleryesbutton);
        infoconsolepanel.add(TempCreater.templabel("添加问题，内容为：", new Font("微软雅黑", 0, 20), respack.getcolor("backgroundcolor"), respack.getcolor("titlefontcolor")));
        infoconsolepanel.add(TempCreater.templabel("", labelfont, respack.getcolor("backgroundcolor"), respack.getcolor("titlefontcolor")));
        infoconsolepanel.add(TempCreater.templabel("", labelfont, respack.getcolor("backgroundcolor"), respack.getcolor("titlefontcolor")));
        subframe_left.add(infoconsolepanel);
        infofilleryesbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Exceptionoutput.outputEvent("Info_filler_yes_Button clicked");
                    if (!about.getText().isEmpty() && !announcernametextfield.getText().isEmpty() && Integer.valueOf(String.valueOf(totaltimefield.getValue())) >= Integer.valueOf(String.valueOf(singletimefield.getValue())) && Integer.valueOf(String.valueOf(totaltimefield.getValue())) <= 9999) {
                        topicprinter();
                        infofilleryesbutton.setEnabled(false);
                        about.setEnabled(false);
                        announcernametextfield.setEnabled(false);
                        totaltimefield.setEnabled(false);
                        singletimefield.setEnabled(false);
                        infotype.setEnabled(false);
                        order.setEnabled(false);
                        resetinfofieldscolor();
                        screennexttextarea.append("待刷新" + infotype.getItemAt(infotype.getSelectedIndex()).toString() + "\n");
                        TempCreater.tempnoticePopmenu("已提交，刷新屏幕将内容更新到主屏", 10000).show(subframe_left, infofiller.getX() + 50, infofiller.getY() + infofiller.getHeight());
                    } else {
                        if (about.getText().isEmpty()) {
                            about.setBackground(respack.getcolor("warningbuckcolor"));
                            about.setForeground(respack.getcolor("warningforecolor"));
                        }
                        if (announcernametextfield.getText().isEmpty()) {
                            announcernametextfield.setBackground(respack.getcolor("warningbuckcolor"));
                            announcernametextfield.setForeground(respack.getcolor("warningforecolor"));
                        }
                        if (Integer.valueOf(String.valueOf(totaltimefield.getValue())) < Integer.valueOf(String.valueOf(singletimefield.getValue()))) {
                            totaltimefield.setBackground(respack.getcolor("warningbuckcolor"));
                            totaltimefield.setForeground(respack.getcolor("warningforecolor"));
                            singletimefield.setBackground(respack.getcolor("warningbuckcolor"));
                            singletimefield.setForeground(respack.getcolor("warningforecolor"));
                            TempCreater.tempnoticePopmenu("分时长大于总时长", 5000).show(subframe_left, infofiller.getX() + infofiller.getWidth() - 50, infofiller.getY() + infofiller.getHeight());
                        }
                        if (Integer.valueOf(String.valueOf(totaltimefield.getValue())) > 9999) {
                            totaltimefield.setBackground(respack.getcolor("warningbuckcolor"));
                            TempCreater.tempnoticePopmenu("时间太长了!", 5000).show(subframe_left, infofiller.getX() + infofiller.getWidth() - 50, infofiller.getY() + infofiller.getHeight());
                        }
                    }
                } catch (Exception e2) {
                    Exceptionoutput.outputException(e2);
                }
            }
        });
        infofillerclearbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exceptionoutput.outputEvent("Info_filler_clear_Button clicked");
                if (MessageWindow.showMessageWindow(2, "确定要清除内容吗？").equals("0")) {
                    if (!about.getText().isEmpty() && !about.isEnabled()) {
                        recordtextarea.append(LocalTime.now().toString() + "：上一条动议在被更新到屏幕之前被清除\r\r\n\n");
                    }
                    infotype.setSelectedIndex(0);
                    about.setText("");
                    announcernametextfield.setText("");
                    totaltimefield.setValue(0);
                    singletimefield.setValue(0);
                    infofilleryesbutton.setEnabled(true);
                    about.setEnabled(true);
                    totaltimefield.setEnabled(true);
                    announcernametextfield.setEnabled(true);
                    singletimefield.setEnabled(true);
                    infotype.setEnabled(true);
                    order.setEnabled(true);
                    resetinfofieldscolor();
                }
            }
        });

        questionpanel.setBackground(respack.getcolor("backgroundcolor"));
        questionpanel.setLayout(TempCreater.tempgridlayout(2, 3, 5, 5));
        questionpanel.add(questionfield);
        questionpanel.add(questiontype);
        JButton questionclearbutton = new JButton("重置以上内容");
        questionpanel.add(questionclearbutton);
        questionpanel.add(questionyesbutton);
        subframe_left.add(questionpanel);
        questionyesbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exceptionoutput.outputEvent("question_yes_Button clicked");
                if (!questionfield.getText().isEmpty()) {
                    questionfield.setBackground(respack.getcolor("textfielddefaultbuckcolor"));
                    recordtextarea.append(LocalTime.now().toString() + "：" + questionfield.getText() + " 提出 " + questiontype.getItemAt(questiontype.getSelectedIndex()) + "\r\r\n\n");
                    questionfield.setEnabled(false);
                    questionyesbutton.setEnabled(false);
                    questiontype.setEnabled(false);
                    screennexttextarea.append("待刷新" + questiontype.getItemAt(questiontype.getSelectedIndex()).toString() + "\n");
                    TempCreater.tempnoticePopmenu("已提交，刷新屏幕将内容更新到主屏", 10000).show(subframe_left, infofiller.getX() + 50, infofiller.getY() + infofiller.getHeight());
                } else {
                    questionfield.setBackground(respack.getcolor("warningbuckcolor"));
                }
            }
        });
        questionclearbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exceptionoutput.outputEvent("question_clear_Button clicked");
                if (MessageWindow.showMessageWindow(2, "确认要清除内容吗？").equals("0")) {
                    if (!questionfield.getText().isEmpty() && !questionfield.isEnabled()) {
                        recordtextarea.append(LocalTime.now().toString() + "：上一条问题在更新到屏幕之前被清除\r\r\n\n");
                    }
                    questionfield.setBackground(respack.getcolor("textfielddefaultbuckcolor"));
                    questionfield.setText("");
                    questionfield.setEnabled(true);
                    questionyesbutton.setEnabled(true);
                    questiontype.setEnabled(true);
                }
            }
        });

        noticeanderrortextarea.setBackground(respack.getcolor("backgroundcolor"));
        noticeanderrortextarea.setForeground(respack.getcolor("warningbuckcolor"));
        noticeanderrortextarea.setFont(new Font("微软雅黑", 0, 10));
        noticeanderrortextarea.setLineWrap(true);
        JScrollPane noticeanderrorpanel = new JScrollPane(noticeanderrortextarea);
        subframe_left.add(noticeanderrorpanel);

        clockfieldpanel.setBackground(respack.getcolor("backgroundcolor"));
        clockfieldpanel.setLayout(TempCreater.tempgridlayout(1, 4, 3, 3));
        clocktotaltextfield.setEditable(false);
        clocksingletextfield.setEditable(false);
        clocktotaltextfield.setBorder(null);
        clocktotaltextfield.setForeground(respack.getcolor("textfielddefaultbuckcolor"));
        clocktotaltextfield.setBackground(respack.getcolor("backgroundcolor"));
        clocktotaltextfield.setFont(respack.getFont("defaulttextfont"));
        clocksingletextfield.setBorder(null);
        clocksingletextfield.setForeground(respack.getcolor("textfielddefaultbuckcolor"));
        clocksingletextfield.setBackground(respack.getcolor("backgroundcolor"));
        clocksingletextfield.setFont(respack.getFont("defaulttextfont"));
        clockfieldpanel.add(TempCreater.templabel("总时长", respack.getFont("defaulttextfont"), respack.getcolor("backgroundcolor"), respack.getcolor("textfielddefaultbuckcolor")));
        clockfieldpanel.add(clocktotaltextfield);
        clockfieldpanel.add(TempCreater.templabel("分时长", respack.getFont("defaulttextfont"), respack.getcolor("backgroundcolor"), respack.getcolor("textfielddefaultbuckcolor")));
        clockfieldpanel.add(clocksingletextfield);

        clockcontrolpanel.setLayout(TempCreater.tempgridlayout(1, 4, 5, 5));
        clockcontrolpanel.setBackground(respack.getcolor("backgroundcolor"));
        pauseclockbutton.setEnabled(false);
        clockcontrolpanel.add(startclockbutton);
        clockcontrolpanel.add(pauseclockbutton);
        clockcontrolpanel.add(nexttimebutton);
        clockcontrolpanel.add(clearclockbutton);
        clockpanel.setLayout(TempCreater.tempgridlayout(2, 1, 5, 5));
        clockpanel.setBackground(respack.getcolor("backgroundcolor"));
        clockpanel.add(clockfieldpanel);
        clockpanel.add(clockcontrolpanel);
        subframe_left.add(clockpanel);
        startclockbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exceptionoutput.outputEvent("Start_clock_Button clicked");
                clocker.running = true;
                pauseclockbutton.setEnabled(true);
                startclockbutton.setEnabled(false);
            }
        });
        pauseclockbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exceptionoutput.outputEvent("Pause_clock_Button clicked");
                clocker.running = false;
                pauseclockbutton.setEnabled(false);
                startclockbutton.setEnabled(true);
            }
        });
        nexttimebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exceptionoutput.outputEvent("Next_time_Button clicked");
                clocker.running = false;
                startclockbutton.setEnabled(true);
                pauseclockbutton.setEnabled(false);
                if (Integer.valueOf(clocktotaltextfield.getText()) > Integer.valueOf(clocksingletextfield.getText())) {
                    clocktotaltextfield.setText(String.valueOf(Integer.valueOf(clocktotaltextfield.getText()) - Integer.valueOf(clocksingletextfield.getText())));
                    ScreenWindow.totaltimelabel.setText(clocktotaltextfield.getText() + " s");
                    clocksingletextfield.setText(String.valueOf(singletime));
                    ScreenWindow.singletimelabel.setText(String.valueOf(singletime) + " s");
                } else {
                    clocksingletextfield.setText("0");
                    ScreenWindow.singletimelabel.setText("0s");
                }
                ScreenWindow.colorpanel.setBackground(new Color(255, 150, 0));
                ScreenWindow.infoactionlabel.setText("本次计时被手动结束");
                ScreenWindow.infoaction(3);
                MessageWindow.showMessageWindow(0, "本次计时被手动结束");
            }
        });
        clearclockbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exceptionoutput.outputEvent("Clear_clock_Button clicked");
                if (MessageWindow.showMessageWindow(2, "确认要清空计时器吗?").equals("0")) {
                    clocktotaltextfield.setText("0");
                    clocksingletextfield.setText("0");
                    ScreenWindow.totaltimelabel.setText("0000s");
                    ScreenWindow.singletimelabel.setText("0000s");
                    totaltime = 0;
                    singletime = 0;
                }
            }
        });

        openscreenmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recordtextarea.append(LocalTime.now().toString() + "：控制台打开了大屏幕\r\r\n\n");
                screennowpanel.setBackground(respack.getcolor("screenbuckcolor"));
                openscreenmenuitem.setEnabled(false);
                closescreenmenuitem.setEnabled(true);
                screencontrol(0);
            }
        });
        refreshscreenmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MessageWindow.showMessageWindow(2, "确认将新的内容刷新至主屏幕？\n" +
                        "提示：计时器会随主屏幕的更新而更新").equals("0")) {
                    if (!about.isEnabled() || !questionfield.isEnabled()) {
                        screencontrol(1);
                        try {
                            totaltime = Integer.valueOf(String.valueOf(totaltimefield.getValue()));
                            clocktotaltextfield.setText(String.valueOf(totaltime));
                            ScreenWindow.totaltimelabel.setText(String.valueOf(totaltime) + " s");
                        } catch (Exception e1) {
                            totaltime = 0;
                            totaltimefield.setBackground(respack.getcolor("warningbuckcolor"));
                            Exceptionoutput.outputException(e1);
                        }
                        try {
                            singletime = Integer.valueOf(String.valueOf(singletimefield.getValue()));
                            clocksingletextfield.setText(String.valueOf(singletime));
                            ScreenWindow.singletimelabel.setText(String.valueOf(singletime) + " s");
                        } catch (Exception e1) {
                            singletime = 0;
                            singletimefield.setBackground(respack.getcolor("warningbuckcolor"));
                            Exceptionoutput.outputException(e1);
                        }
                        clearandresetall();
                        recordtextarea.append(LocalTime.now().toString() + "：内容已更新至主屏幕");
                    }
                }
            }
        });
        closescreenmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recordtextarea.append(LocalTime.now().toString() + "：控制台关闭了大屏幕\r\r\n\n");
                screennowpanel.setBackground(new Color(111, 111, 111));
                openscreenmenuitem.setEnabled(true);
                closescreenmenuitem.setEnabled(false);
                screencontrol(2);
            }
        });

        try {
            Thread.sleep(100);
        } catch (Exception e) {
            Exceptionoutput.outputException(e);
        }
    }

    private void resetinfofieldscolor() {
        about.setBackground(respack.getcolor("textfielddefaultbuckcolor"));
        announcernametextfield.setBackground(respack.getcolor("textfielddefaultbuckcolor"));
        totaltimefield.setBackground(respack.getcolor("textfielddefaultbuckcolor"));
        singletimefield.setBackground(respack.getcolor("textfielddefaultbuckcolor"));
        questionfield.setBackground(respack.getcolor("textfielddefaultbuckcolor"));
        about.setForeground(respack.getcolor("textfielddefaultforecolor"));
        announcernametextfield.setForeground(respack.getcolor("textfielddefaultforecolor"));
        totaltimefield.setForeground(respack.getcolor("textfielddefaultforecolor"));
        singletimefield.setForeground(respack.getcolor("textfielddefaultforecolor"));
        questionfield.setForeground(respack.getcolor("textfielddefaultforecolor"));
        about.setFont(respack.getFont("defaulttextfont;"));
        announcernametextfield.setFont(respack.getFont("defaulttextfont;"));
        totaltimefield.setFont(respack.getFont("defaulttextfont;"));
        singletimefield.setFont(respack.getFont("defaulttextfont;"));
        questionfield.setFont(respack.getFont("defaulttextfont;"));
    }

    private void resetinfotype() {
        infotype.addItem("有主持核心磋商");
        infotype.addItem("自由磋商");
        infotype.addItem("发言名单操作");
        infotype.addItem("有主题辩论");
        infotype.addItem("自由辩论");
        infotype.addItem("休会");
        order.addItem("选择申请方发言顺序");
        order.addItem("提出方申请首位发言");
        order.addItem("提出方申请末位发言");
    }

    public void twitclick() {
        ScreenWindow.twiterlabel.setText(twitertextfield.getText());
        recordtextarea.append(LocalTime.now() + "：控制台推送了消息[" + twitertextfield.getText() + "]\r\r\n\n");
        twitertextfield.setText("");
        ScreenWindow.infoactionlabel.setText("有新的推送消息");
        ScreenWindow.colorpanel.setBackground(new Color(123, 123, 123));
        ScreenWindow.questionaction(3);
        class waitthr extends Thread {
            @Override
            public void run() {
                twitbutton.setEnabled(false);
                twitertextfield.setEnabled(false);
                try {
                    for (int tempint = 3; tempint >= 0; tempint--) {
                        twitertextfield.setText("防刷屏 " + String.valueOf(tempint) + " 秒");
                        Thread.sleep(1000);
                    }
                } catch (Exception e1) {
                    Exceptionoutput.outputException(e1);
                }
                twitertextfield.setText("");
                twitertextfield.setEnabled(true);
                twitbutton.setEnabled(true);
            }
        }
        waitthr thrs = new waitthr();
        thrs.start();
    }

    private void clearandresetall() {
        infofilleryesbutton.setEnabled(true);
        about.setEnabled(true);
        totaltimefield.setEnabled(true);
        announcernametextfield.setEnabled(true);
        singletimefield.setEnabled(true);
        infotype.setEnabled(true);
        questiontype.setEnabled(true);
        questionyesbutton.setEnabled(true);
        questionfield.setEnabled(true);
        order.setEnabled(true);
        about.setText("");
        questionfield.setText("");
        announcernametextfield.setText("");
        totaltimefield.setValue(0);
        singletimefield.setValue(0);
        screennexttextarea.setText("");
        questiontype.setSelectedIndex(0);
    }

    private void resetquestiontype() {
        questiontype.addItem("程序性问题");
        questiontype.addItem("资讯性问题");
        questiontype.addItem("个人特权问题");
        questiontype.addItem("使用答辩权");
        questiontype.addItem("使用质询权");
    }

    private void topicprinter() {
        try {
            recordtextarea.append(LocalTime.now().toString() + "：" + announcernametextfield.getText() + " 提出了关于 " + about.getText() + " 的" + infotype.getItemAt(infotype.getSelectedIndex()) + "，");
            if (Integer.valueOf(String.valueOf(totaltimefield.getValue())) != 0)
                recordtextarea.append("以上议题总时长：" + Integer.valueOf(String.valueOf(totaltimefield.getValue())));
            if (Integer.valueOf(String.valueOf(totaltimefield.getValue())) != 0 && Integer.valueOf(String.valueOf(singletimefield.getValue())) != 0)
                recordtextarea.append("   分时长：" + Integer.valueOf(String.valueOf(singletimefield.getValue())) + "，共可容纳" + Integer.valueOf(Integer.valueOf(String.valueOf(totaltimefield.getValue()))) / Integer.valueOf(String.valueOf(singletimefield.getValue())) + "名代表发言");
            if (order.getSelectedIndex() != 0)
                recordtextarea.append(" ， " + order.getItemAt(order.getSelectedIndex()));
            recordtextarea.append("\r\r\n\n");
        } catch (Exception e) {
            Exceptionoutput.outputException(e);
        }
    }

    private void screencontrol(int action) {
        if (action == 0) /*open screen*/ {
            screen.border.setBackground(new Color(0, 36, 78));
            try {
                screen.tocolor = respack.getcolor("screenbuckcolor");
                screen.screenwindow.setVisible(true);
                screen.changeusedtimes = 50;
                screen.singlechangetime = 5;
                screen.colorchanger.start();
            } catch (Exception e) {
                Exceptionoutput.outputException(e);
                screen.border.setBackground(respack.getcolor("screenbuckcolor"));
            }
        }
        if (action == 1)/*refresh the display*/ {
            ScreenWindow.refresh();
        }
        if (action == 2)/*close screen*/ {
            screen.screenwindow.setVisible(false);
        }
        /*
        if (action == 3) {
            //reset the screen
        }
        */
    }

    public void reloadcolors() {
        consolewind.setBackground(respack.getcolor("backgroundcolor"));
        consolewind.setBackground(respack.getcolor("backgroundcolor"));
        recordtextarea.setBackground(respack.getcolor("backgroundcolor"));
        buttonFrame.setBackground(respack.getcolor("backgroundcolor"));
        subframe_left.setBackground(respack.getcolor("backgroundcolor"));
        subframe_right.setBackground(respack.getcolor("backgroundcolor"));
        border.setBackground(respack.getcolor("backgroundcolor"));
        border.setBorder(BorderFactory.createLineBorder(respack.getcolor("backgroundcolor"), 20));
        MFLauncher.orderLists.listlist.setBackground(respack.getcolor("backgroundcolor"));
        MFLauncher.orderLists.orderlist.setBackground(respack.getcolor("backgroundcolor"));
        screentwiterpanel.setBackground(respack.getcolor("backgroundcolor"));
        screenactionpanel.setBackground(respack.getcolor("backgroundcolor"));
        twitertextfield.setBackground(respack.getcolor("backgroundcolor"));
        screenviewpanel.setBackground(respack.getcolor("backgroundcolor"));
        screennexttextarea.setBackground(respack.getcolor("backgroundcolor"));
        screennexttextscrollpane.setBackground(respack.getcolor("backgroundcolor"));
        dividerpanel.setBackground(respack.getcolor("backgroundcolor"));
        temppanel.setBackground(respack.getcolor("backgroundcolor"));
        infofiller.setBackground(respack.getcolor("backgroundcolor"));
        infoconsolepanel.setBackground(respack.getcolor("backgroundcolor"));
        //infoconsolepanel.setBackground(new Color(255,2,2));
        //infofiller.setBackground(new Color(0,255,0));
        questionpanel.setBackground(respack.getcolor("backgroundcolor"));
        noticeanderrortextarea.setBackground(respack.getcolor("backgroundcolor"));
        clockcontrolpanel.setBackground(respack.getcolor("backgroundcolor"));
        clocksingletextfield.setBackground(respack.getcolor("backgroundcolor"));
        clocktotaltextfield.setBackground(respack.getcolor("backgroundcolor"));
        clockfieldpanel.setBackground(respack.getcolor("backgroundcolor"));
        clockpanel.setBackground(respack.getcolor("backgroundcolor"));
        listpanel.setBackground(respack.getcolor("backgroundcolor"));
    }

    public void setFullScreen(boolean flag) {
        Exceptionoutput.outputEvent("Set_full_screen on Console_Wind");
        this.consolewind.setUndecorated(flag);
        this.consolewind.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    }
}
