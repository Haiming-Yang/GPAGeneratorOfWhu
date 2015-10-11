package Model;

import java.util.Comparator;

public class GradeDowner implements Comparator<String[]> {
//	重载排序
	@Override
        public int compare(String[] a, String[] b)
        {
            if (Double.valueOf(a[9]) < Double.valueOf(b[9])){
                return 1;
            }
            else {
            return -1;
            }
        }
	
    }


