package tools;

import com.wel_record.model.WelRecordJNDIDAO;
import com.wel_record.model.WelRecordVO;

public class FakeDataMaker {

	public static void main(String[] args) {

		// --------------------------�����-�H���s�Wn���������----------------------------------
		WelRecordJNDIDAO welRecordDAO = new WelRecordJNDIDAO();
		int[] srcArray = { 10, 20, 30, 31, 32, 33, 34, 35, 36, 37, 38, 40, 41, 42, 43 };
		String[] memArray = { "M000001", "M000002", "M000003", "M000004" };

		int n = 20;
		for (int i = 0; i < n; i++) {
			System.out.println(i);
			// �H��������ӷ�srcArray&memArray���޸�
			int r = (int) (Math.random() * 100 + 1); // �H������@�Ʀr(1-100��)
			int memIndex = (int) (Math.random() * memArray.length);
			int amount = ((int) (Math.random() * 100) + 1) * 100;
			int amountShare = (int) (amount * 0.15);
			int srcIndex = 0;

			WelRecordVO welRecordVO2 = new WelRecordVO();
			welRecordVO2.setMem_id(memArray[memIndex]);
			welRecordVO2.setOrder_id(null);

			if ((r > 0 && r <= 33)) // 1/3���v���x��
			{

				welRecordVO2.setTns_src(10);
				welRecordVO2.setTns_amount(amount);

			} else if (r > 33 && r <= 43) // ��1/10������
			{
				welRecordVO2.setTns_src(20);
				welRecordVO2.setTns_amount(-amount);

			} else if (r > 43 && r <= 53) // ��1/10�����x�h��

			{
				srcIndex = (int) (Math.random() * 3 + 35); // 35-38������@�Ʀr
				welRecordVO2.setTns_src(srcIndex);
				welRecordVO2.setTns_amount((int) (amount * 0.3)); // �h�ڪ��B������x�ȤT��

			} else if (r > 63 && r <= 73) // ��1/10�� ����+�馩��

			{
				srcIndex = (int) (Math.random() * 4 + 30); // 30-34������@�Ʀr
				welRecordVO2.setTns_src(srcIndex);
				welRecordVO2.setTns_amount(amountShare);

			} else {
				srcIndex = (int) (Math.random() * 3 + 40); // 40-43������@�Ʀr
				welRecordVO2.setTns_src(srcIndex);
				welRecordVO2.setTns_amount((int) (-amount * 0.8)); // 1/3���v�����x����
			}

			welRecordDAO.insert(welRecordVO2);
		}

//		10:�|���x��
//		20:�|������
//		30:���x����-�@���ʶR����
//		31:���x����-�w�ʤ���
//		32:���x����-�v�Ф���
//		33:���x����-�����|����
//		34:���x����-�w�ʧ馩��
//		35:���x�h��-�@���ʶR�h��
//		36:���x�h��-�w��
//		37:���x�h��-�v��
//		38:���x�h��-�����|
//		40:���x����-�@���ʶR�q��
//		41:���x����-�w�ʭq��
//		42:���x����-�v�Эq��
//		43:���x����-�����|

	}

}
