package pack.business;

import java.util.List;

public interface ProcessInter {
	List<DataDto> selectDataAll(); // 추상으로 만들었다
	DataDto selectPart(String para); // 추상으로 만들었다
	boolean insertData(DataFormBean form); // 추상으로 만들었다
	boolean updateData(DataFormBean form); // 추상으로 만들었다
	boolean deleteData(String id); // 추상으로 만들었다
}
