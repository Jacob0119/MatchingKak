package project.capstone.com.matchingkak.Main.alarm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.capstone.com.matchingkak.ActivityStarterManager;
import project.capstone.com.matchingkak.GameItemViewUtils;
import project.capstone.com.matchingkak.Main.alarm.data.alarmItem;
import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.config;
import project.capstone.com.matchingkak.databinding.FragmentItemListDialogBinding;
import project.capstone.com.matchingkak.restAPI.APIUrl;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     ItemListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 * <p>You activity (or fragment) needs to implement {@link ItemListDialogFragment.Listener}.</p>
 */
public class BottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener{
    FragmentItemListDialogBinding binding;
    private alarmItem item;
    public static BottomSheetFragment newInstance(alarmItem item) {
        final BottomSheetFragment fragment = new BottomSheetFragment();
        final Bundle args = new Bundle();
        args.putParcelable(config.ALARM_ITEM, item);
        fragment.setArguments(args);
        return fragment;

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_item_list_dialog,container,false);
        binding.setDialog(this);
        return binding.getRoot();

    }

    @Override
    public void onStart() {
        super.onStart();
        this.item=getArguments().getParcelable(config.ALARM_ITEM);
        binding.alarmDialogClear.setOnClickListener(this);
        binding.alarmDialogDelete.setOnClickListener(this);
        binding.alarmDialogDetail.setOnClickListener(this);
        binding.alarmDialogLike.setOnClickListener(this);
        binding.alarmDialogMessage.setOnClickListener(this);

        binding.alarmDialogName.setText(item.getUser());
        GameItemViewUtils.setCircleImage(binding.alarmDialogImage, APIUrl.API_BASE_URL+item.getTm_img());
    }

    @Override
    public void onClick(View view) {
        dismiss();
        switch (view.getId()){

            case R.id.alarm_dialog_detail:
                ActivityStarterManager.StartGameDetailActivity(view.getContext(),item.getRq_count_no());
                break;
            case R.id.alarm_dialog_message:
                ActivityStarterManager.StartWriteMessageActivity(view.getContext(),item.getUser());

        }
        dismiss();
    }


}
