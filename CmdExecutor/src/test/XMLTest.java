package test;

import java.beans.XMLDecoder;
import java.util.List;
import org.jdom2.Element;
import org.junit.Test;
import com.netease.B6646_zx_Function.*;

public class XMLTest {
	XMLEditor editor = new XMLEditor("D:\\temp\\Svc.xml");
	XMLEditor editor1 = new XMLEditor("D:\\temp\\Config.xml");
	//测试构造方法
	@Test
	public void testConstructor() {
		
		new XMLEditor("D:\\a.xml");
	}
	
	//测试Config.xml文件Method的格式是否正确
	@Test
	public void  testCfgMethod(){
		testGitMethod();

	}
	
	@Test
	public void testCfgUI(){
		testGitUI();
	}
	
	@Test
	public void testAddXmlContent(){
		Element element = new Element("呵呵");
		element.addContent(new Element("aaa"));
		element.addContent(new Element("bbb"));
		XMLEditor editor = new XMLEditor("D:\\temp\\Svc.xml");
		editor.addElelemt("Svc-Git-哈哈", element	);
	}
	
	@Test
	public void testRemoveElement() {
		editor.removeElement("Svc-Git-haha-command");
	}
	
	@Test
	public void testSwitchElement(){
		editor.switchElementByText("Svc-Git", 0, 1);
	}
	
	@Test
	public void testInsertElement(){
		Element e = new Element("aaa");
		e.addContent(new Element("bb"));
		e.addContent(new Element("c"));
		editor.insertElement("Svc-Git",2,e);
	}
	
	@Test
	public void testReplace(){
		Element e = new Element("a1111");
		e.addContent(new Element("b22"));
		e.addContent(new Element("c3"));
		editor.replaceEmelement("Svc-Git", 2, e);
	}
	
	@Test
	public void testClear(){
		editor.clearElement("Svc-Git-a1111");
		editor.addElelemt("Svc-Git-a1111", new Element("aabbcc"));
	}
	
	@Test
	public void testGetName(){
		editor.getName("Svc-Git", 3);
	}
	
	@Test 
	public void testGetAll(){
		List<Element> e = editor1.getAllElements("Config-UI");
		for (int i = 0; i < e.size(); i++) {
			System.out.println(e.get(i).getName().trim());
		}
	}
	
	@Test 
	public void testGetByName(){
		List<Element> e = editor1.getElementsByName("Config-UI", "Git");
		for (int i = 0; i < e.size(); i++) {
			System.out.println(e.get(i).getName().trim());
		}
	}
	
	@Test
	public void testgetValueByPathandIndex(){
		String path = "Setting-UI-Git";
		int index=1;
		editor1.getValueByPathandIndex(path,index);
		
	}
	
	/****************************/
	/**********private***********/
	/****************************/
	private void testGitMethod() {
		// TODO Auto-generated method stub
		XMLEditor editor = new XMLEditor("D:\\temp\\Config.xml");
		
		Element git = editor.getRootElement().getChild("Function").getChild("git");
		String path = git.getChildText("filePath");
		System.out.println(String.format("Config.xml的路径为： %s..", path));
		System.out.println(11111);
		Element mappping = git.getChild("mapping");
		List<Element> list = mappping.getChildren("Method");
		System.out.println(String.format("总共有%d个方法", list.size()));
		for (int i = 0; i < list.size(); i++) {
			String p1 = list.get(i).getAttributeValue("name");
			String p2 = list.get(i).getAttributeValue("cmd");
			String p3 = list.get(i).getAttributeValue("desc");
			System.out.println(String.format("(A)方法名字:%s ," +
					" (A)相应命令:%s, (A)方法描述: %s",p1,p2,p3 ));
			//InnerMethod相关
			List<Element> e = list.get(i).getChildren("InnerMethod");
			for (int j = 0; j < e.size(); j++) {
				String name = e.get(j).getText();
				String nec = e.get(j).getAttributeValue("necessary");
				String text = e.get(j).getAttributeValue("text");
				System.out.println(String.format("      Inner-名字:%s," +
						" (A)必须:%s, (A)文案:%s", name,nec,text));
			}
		}
	}
	


	private void testGitUI() {
		// TODO Auto-generated method stub
		XMLEditor editor = new XMLEditor("D:\\temp\\Config.xml");
		Element gitUI = editor.getRootElement().getChild("UI").getChild("type");
		List<Element> list = gitUI.getChildren("desc");
		System.out.println(String.format("现在有%d条GitUI相关记录。", list.size()));
		for (int i = 0; i < list.size(); i++) {
			Element e = list.get(i);
			String name = e.getAttributeValue("name");
			String local = e.getChildText("localDir");
			String remote = e.getChildText("remoteDir");
			System.out.println(String.format("(A)描述:%s," +
					"\n        本地目录:%s,\n        远端目录:%s",name,local,remote));
		}
	}
}
