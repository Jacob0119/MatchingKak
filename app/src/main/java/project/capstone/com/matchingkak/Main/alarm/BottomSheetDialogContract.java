package project.capstone.com.matchingkak.Main.alarm;

/**
 * Created by Lee on 2018-04-14.
 */

public interface BottomSheetDialogContract {
   interface Listener {
       final static int LIKE = 1;
       final static int DELETE = 2;
       final static int MESSAGE = 3;
       final static int DETAIL = 4;
       final static int SUBMIT = 5;
       final static int REJECT = 6;

       void OnClickListener(int ResultCode, int position);
   }

   interface Dialog{

       void attachListener(BottomSheetDialogContract.Listener listener);


   }
}
