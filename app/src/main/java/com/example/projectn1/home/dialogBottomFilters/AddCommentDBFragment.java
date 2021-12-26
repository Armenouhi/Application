package com.example.projectn1.home.dialogBottomFilters;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectn1.R;
import com.example.projectn1.home.comments.Comment;
import com.example.projectn1.home.comments.CommentsAdapter;
import com.example.projectn1.home.comments.OnSaveData;
import com.example.projectn1.notification.NotificationActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddCommentDBFragment extends BottomSheetDialogFragment implements OnSaveData {
    CommentsAdapter adapterComment = new CommentsAdapter();
    View view;
    AppCompatButton saveComment;

    NotificationManager nM;
    NotificationChannel nCh;
    Notification.Builder builder;
    static RemoteViews[] contentView;
    private String channelId = "i.apps.notifications";
    private String description = "Test notification";

    public static AddCommentDBFragment newInstance() {
        return new AddCommentDBFragment();
    };

    @Nullable
    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("RemoteViewLayout")
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        view = inflater.inflate(
                R.layout.dialog_bottom_comment,
                container, false
        );

        saveComment = view.findViewById(R.id.saveComment);

        listComments();
        saveComments();

        return view;
    }


    private void listComments() {
        RecyclerView recyclerView = view.findViewById(R.id.recCommentView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                getContext(),
                1,
                RecyclerView.VERTICAL,
                false
        );

        recyclerView.setLayoutManager(gridLayoutManager);

        adapterComment.setComments(this);
        adapterComment.setCommentskist(Comment.getComments());
        recyclerView.setAdapter(adapterComment);
    }

    @Override
    public void onSave(AppCompatButton save) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void saveComments() {
        nM = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        contentView = new RemoteViews[1];

        saveComment.setOnClickListener(v -> {
            System.out.println("Save data");

            Intent intent = new Intent(getContext(), DialogBottomCommentsActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, 0);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                nCh = new NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH);
                nCh.enableLights(true);
                nCh.setLightColor(Color.GREEN);
                nCh.enableVibration(false);
                nM.createNotificationChannel(nCh);

                builder = new Notification.Builder(getContext(), channelId)
                        .setCustomContentView(contentView[0])
                        .setSmallIcon(R.drawable.gmail)
                        .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_email_read))
                        .setContentTitle("Comments")
                        .setContentText("Comment 1")
                        .setStyle(new Notification.Style() {
                            @Override
                            protected void internalSetBigContentTitle(CharSequence title) {
                                super.internalSetBigContentTitle(title);
                            }
                        })
                        .setContentIntent(pendingIntent);


            } else {
                builder = new Notification.Builder(getContext())
                        .setCustomContentView(contentView[0])
                        .setSmallIcon(R.drawable.gmail)
                        .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_email_read))
                        .setContentIntent(pendingIntent);
            }
            nM.notify(27, builder.build());
        });
    }
}
