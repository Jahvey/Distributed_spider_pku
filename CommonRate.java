import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class CommonRate {
	/**
	 * 读取文件，返回文件中所有不重复的单词的list
	 * @param file 文件路径
	 * @return
	 */
	public List<String> noCommonWords(String file){
		FileInputStream input = null;
		List<String> list = null;
		List<String> list2 = null;
		try {
			//读入文件
			input = new FileInputStream(file);
			byte[] tmp = new byte[1024];
			int length = input.read(tmp);
			//将文件里的信息保存为字符串
			String info = new String(tmp,0,length);
			//以空格符为单位分开每个单词
			String[] infoArr = info.split("\\s+");
			//将所有单词放入list
			list = new ArrayList<String>();
			int i;
			for(i=0;i<infoArr.length;i++){
				list.add(infoArr[i]);
			}
			//对单词按字典里的顺序进行排序
			Collections.sort(list);
			list2 = new ArrayList<String>();
			list2.add(list.get(0));
			for(i=1;i<infoArr.length;i++){
				//排序后相同的单词会挨在一起，如果和前一个单词不相同就加入list2
				if(!(list.get(i).equals(list.get(i-1)))){
					list2.add(list.get(i));
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(input!=null){
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list2;
	}
	/**
	 * 找出同时出现在两个文件中的单词并放进一个新的list中
	 * @param list1 第一个文件不重复单词的list
	 * @param list2 第二个文件不重复单词的list
	 * @return
	 */
	public List<String> commonWordsInTwoFiles(List<String> list1, List<String> list2){
		List<String> list = new ArrayList<String>();

		for (int i = 0, j = 0, len1 = list1.size(), len2 = list2.size(); i < len1 && j < len2; ) {
            if (list1.get(i).equals(list2.get(j))) {
                list.add(list1.get(i));
                i++;
                j++;
            }else if (list1.get(i).compareTo(list2.get(j))>0){
            	j++;
            }else{
            	i++;
            }
        }
		return list;
	}
	/**
	 * 一个个输出list里的内容
	 * @param list
	 */
	public void outPutList(List<String> list){
		Iterator it = list.iterator();
		while(it.hasNext()){
			System.out.print(it.next()+" ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		CommonRate cr = new CommonRate();
		
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		
		list1 = cr.noCommonWords("E:\\file1.txt");
		System.out.print("第一个文件中所有不重复的单词为： ");
		cr.outPutList(list1);
		System.out.println("第一个文件中不重复的单词个数为： "+list1.size());
		
		list2 = cr.noCommonWords("E:\\file2.txt");
		System.out.print("第二个文件中所有不重复的单词为： ");
		cr.outPutList(list2);
		System.out.println("第二个文件中不重复的单词个数为： "+list2.size());
		
		System.out.print("同时出现在两个文件中的单词有: ");
		list3 = cr.commonWordsInTwoFiles(list1,list2);
		cr.outPutList(list3);
		System.out.println("两个文件中重叠单词个数为: "+list3.size());
		
		System.out.println("重叠单词个数在第一个文件词汇表中所占的百分比为: "+(list3.size()*1.0)/(list1.size())*100+"%");
		System.out.println("重叠单词个数在第二个文件词汇表中所占的百分比为: "+(list3.size()*1.0)/(list2.size())*100+"%");
	}

}
