package com.example.projectn1.home.dialogBottomFilters;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectn1.R;
import com.example.projectn1.home.comments.CommentsAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class AddCommentDBFragment extends BottomSheetDialogFragment {
    CommentsAdapter adapterComment = new CommentsAdapter();
    View view;
    AppCompatButton saveComment;
    AppCompatEditText commentText;
    ArrayList<String> commentList;

    NotificationManager nM;
    NotificationChannel nCh;
    Notification.Builder builder;


    public static AddCommentDBFragment newInstance() {
        return new AddCommentDBFragment();
    }

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
        commentText = view.findViewById(R.id.addComment);

        loadComments();

        listComments();
        saveComments();

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void saveComments() {
        saveComment.setOnClickListener(v -> {

            if (getActivity() != null) {
                SharedPreferences sharedPreferences =
                        getActivity().getSharedPreferences("Shared Preferences", Context.MODE_PRIVATE);

                @SuppressLint("CommitPrefEdits")
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();

                if (commentText.getText() != null) {
                    commentList.addAll(Collections.singleton(commentText.getText().toString()));
                    String json = gson.toJson(commentList);
                    System.out.println(commentText.getText().toString());
                    editor.putString("Comments", json);
                    editor.apply();

                }

            }
            dismiss();
            sendNotification();
        });
    }

    public void loadComments() {
        try {
            if (getActivity() != null) {
                SharedPreferences sharedPreferences =
                        getActivity().getSharedPreferences("Shared Preferences", Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String json = sharedPreferences.getString("Comments", null);
                Type type = new TypeToken<ArrayList<String>>() {}.getType();
                commentList = gson.fromJson(json, type);

            if (commentList == null) {
                commentList = new ArrayList<>();
                commentText.setPressed(true);
                commentText.requestFocus();
            }

                System.out.println(commentList);
            }
        } catch (Exception e) {
            commentText.setPressed(true);
            commentText.requestFocus();
        }

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

        adapterComment.setCommentskist(commentList);
        recyclerView.setAdapter(adapterComment);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sendNotification() {

        if (getActivity() != null) {
            nM = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        }

            System.out.println("Save data");

            Intent intent = new Intent(getContext(), DialogBottomCommentsActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            @SuppressLint("UnspecifiedImmutableFlag")
            PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, 0);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId = "i.apps.notifications";
                String description = "Test notification";
                nCh = new NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH);
                nCh.enableLights(true);
                nCh.enableVibration(false);
                nM.createNotificationChannel(nCh);

                builder = new Notification.Builder(getContext(), channelId)
                        .setSmallIcon(R.drawable.comment)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_email_read))
                        .setContentTitle("Comments")
                        .setContentText((commentText.getText() != null) ? commentText.getText().toString() : "")
                        .setStyle(new Notification.BigTextStyle())
                        .setStyle(new Notification.BigPictureStyle())
                        .setColor(Color.blue(R.color.blue_100))
                        .setContentIntent(pendingIntent);

            } else {
                builder = new Notification.Builder(getContext())
                        .setSmallIcon(R.drawable.comment)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_email_read))
                        .setContentIntent(pendingIntent);
            }
            nM.notify(128, builder.build());
    }
}
