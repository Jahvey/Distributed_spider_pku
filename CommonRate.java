import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class CommonRate {
	/**
	 * ��ȡ�ļ��������ļ������в��ظ��ĵ��ʵ�list
	 * @param file �ļ�·��
	 * @return
	 */
	public List<String> noCommonWords(String file){
		FileInputStream input = null;
		List<String> list = null;
		List<String> list2 = null;
		try {
			//�����ļ�
			input = new FileInputStream(file);
			byte[] tmp = new byte[1024];
			int length = input.read(tmp);
			//���ļ������Ϣ����Ϊ�ַ���
			String info = new String(tmp,0,length);
			//�Կո��Ϊ��λ�ֿ�ÿ������
			String[] infoArr = info.split("\\s+");
			//�����е��ʷ���list
			list = new ArrayList<String>();
			int i;
			for(i=0;i<infoArr.length;i++){
				list.add(infoArr[i]);
			}
			//�Ե��ʰ��ֵ����˳���������
			Collections.sort(list);
			list2 = new ArrayList<String>();
			list2.add(list.get(0));
			for(i=1;i<infoArr.length;i++){
				//�������ͬ�ĵ��ʻᰤ��һ�������ǰһ�����ʲ���ͬ�ͼ���list2
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
	 * �ҳ�ͬʱ�����������ļ��еĵ��ʲ��Ž�һ���µ�list��
	 * @param list1 ��һ���ļ����ظ����ʵ�list
	 * @param list2 �ڶ����ļ����ظ����ʵ�list
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
	 * һ�������list�������
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
		System.out.print("��һ���ļ������в��ظ��ĵ���Ϊ�� ");
		cr.outPutList(list1);
		System.out.println("��һ���ļ��в��ظ��ĵ��ʸ���Ϊ�� "+list1.size());
		
		list2 = cr.noCommonWords("E:\\file2.txt");
		System.out.print("�ڶ����ļ������в��ظ��ĵ���Ϊ�� ");
		cr.outPutList(list2);
		System.out.println("�ڶ����ļ��в��ظ��ĵ��ʸ���Ϊ�� "+list2.size());
		
		System.out.print("ͬʱ�����������ļ��еĵ�����: ");
		list3 = cr.commonWordsInTwoFiles(list1,list2);
		cr.outPutList(list3);
		System.out.println("�����ļ����ص����ʸ���Ϊ: "+list3.size());
		
		System.out.println("�ص����ʸ����ڵ�һ���ļ��ʻ������ռ�İٷֱ�Ϊ: "+(list3.size()*1.0)/(list1.size())*100+"%");
		System.out.println("�ص����ʸ����ڵڶ����ļ��ʻ������ռ�İٷֱ�Ϊ: "+(list3.size()*1.0)/(list2.size())*100+"%");
	}

}
