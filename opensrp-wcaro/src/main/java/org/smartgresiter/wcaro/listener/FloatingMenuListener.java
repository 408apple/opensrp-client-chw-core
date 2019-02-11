package org.smartgresiter.wcaro.listener;

import android.app.Activity;
import android.content.Intent;

import org.smartgresiter.wcaro.R;
import org.smartgresiter.wcaro.activity.FamilyProfileActivity;
import org.smartgresiter.wcaro.activity.FamilyProfileMenuActivity;
import org.smartgresiter.wcaro.activity.FamilyRemoveMemberActivity;
import org.smartgresiter.wcaro.fragment.AddMemberFragment;
import org.smartgresiter.wcaro.fragment.FamilyCallDialogFragment;
import org.smartgresiter.wcaro.util.Constants;

import java.lang.ref.WeakReference;

public class FloatingMenuListener implements OnClickFloatingMenu {
    private WeakReference<Activity> context;
    private String familyBaseEntityId;
    private String familyHead;
    private String primaryCareGiver;

    private FloatingMenuListener(Activity context, String familyBaseEntityId) {
        this.context = new WeakReference<>(context);
        this.familyBaseEntityId = familyBaseEntityId;
    }

    private static FloatingMenuListener instance;

    public static FloatingMenuListener getInstance(Activity context, String familyBaseEntityId) {
        if (instance == null) {
            instance = new FloatingMenuListener(context, familyBaseEntityId);
        } else {
            instance.setFamilyBaseEntityId(familyBaseEntityId);
        }
        return instance;
    }

    public static FloatingMenuListener getInstance() {
        if (instance == null) {
            throw new RuntimeException("Instantiate this view");
        }
        return instance;
    }

    public String getFamilyBaseEntityId() {
        return familyBaseEntityId;
    }

    public void setFamilyBaseEntityId(String familyBaseEntityId) {
        this.familyBaseEntityId = familyBaseEntityId;
    }

    public String getFamilyHead() {
        return familyHead;
    }

    public void setFamilyHead(String familyHead) {
        this.familyHead = familyHead;
    }

    public String getPrimaryCareGiver() {
        return primaryCareGiver;
    }

    public void setPrimaryCareGiver(String primaryCareGiver) {
        this.primaryCareGiver = primaryCareGiver;
    }

    @Override
    public void onClickMenu(int viewId) {
        if (context.get() != null) {
            switch (viewId) {
                case R.id.call_layout:
                    // Toast.makeText(context, "Go to call screen", Toast.LENGTH_SHORT).show();
                    FamilyCallDialogFragment.launchDialog(context.get(), familyBaseEntityId);
                    //go to child add form activity
                    break;
                case R.id.family_detail_layout:

                    ((FamilyProfileActivity) context.get()).startFormForEdit();

                    break;
                case R.id.add_new_member_layout:

                    AddMemberFragment addmemberFragment = AddMemberFragment.newInstance();
                    addmemberFragment.setContext(context.get());
                    addmemberFragment.show(context.get().getFragmentManager(), AddMemberFragment.DIALOG_TAG);

                    break;

                case R.id.remove_member_layout:

                    Intent frm_intent = new Intent(context.get(), FamilyRemoveMemberActivity.class);
                    frm_intent.putExtra(org.smartregister.family.util.Constants.INTENT_KEY.FAMILY_BASE_ENTITY_ID, getFamilyBaseEntityId());
                    frm_intent.putExtra(org.smartregister.family.util.Constants.INTENT_KEY.FAMILY_HEAD, getFamilyHead());
                    frm_intent.putExtra(org.smartregister.family.util.Constants.INTENT_KEY.PRIMARY_CAREGIVER, getPrimaryCareGiver());
                    context.get().startActivityForResult(frm_intent, Constants.ProfileActivityResults.CHANGE_COMPLETED);

                    break;
                case R.id.change_head_layout:

                    Intent fh_intent = new Intent(context.get(), FamilyProfileMenuActivity.class);
                    fh_intent.putExtra(org.smartregister.family.util.Constants.INTENT_KEY.BASE_ENTITY_ID, getFamilyBaseEntityId());
                    fh_intent.putExtra(FamilyProfileMenuActivity.MENU, FamilyProfileMenuActivity.MenuType.ChangeHead);
                    context.get().startActivityForResult(fh_intent, Constants.ProfileActivityResults.CHANGE_COMPLETED);

                    break;
                case R.id.change_primary_layout:

                    Intent pc_intent = new Intent(context.get(), FamilyProfileMenuActivity.class);
                    pc_intent.putExtra(org.smartregister.family.util.Constants.INTENT_KEY.BASE_ENTITY_ID, getFamilyBaseEntityId());
                    pc_intent.putExtra(FamilyProfileMenuActivity.MENU, FamilyProfileMenuActivity.MenuType.ChangePrimaryCare);
                    context.get().startActivityForResult(pc_intent, Constants.ProfileActivityResults.CHANGE_COMPLETED);

                    break;
            }
        }
    }
}
