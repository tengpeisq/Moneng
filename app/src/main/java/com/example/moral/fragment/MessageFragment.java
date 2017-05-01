package com.example.moral.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moral.R;
import com.example.moral.model.Message;
import com.example.moral.retrofit.RApiService;
import com.example.moral.retrofit.RRetrofit;
import com.example.moral.view.RecycleViewDivider;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by imxiaopeng on 2017/4/30.
 */

public class MessageFragment extends BaseFragment {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.refresh)
    SwipeRefreshLayout refresh;
    SharedPreferences sp;
    ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }
    });
    @Override
    protected View setContentView() {
        View view = mInflater.inflate(R.layout.fragment_message, mContainer, false);
        return view;
    }
    @Override
    protected void initView() {
        helper.attachToRecyclerView(recyclerView);
        //布局方式
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        //分割线
        recyclerView.addItemDecoration(new RecycleViewDivider(mContext, LinearLayout.HORIZONTAL));
        refresh.setColorSchemeColors(ContextCompat.getColor(mContext,R.color.colorRb_Checked)
        ,ContextCompat.getColor(mContext,R.color.white)
                ,ContextCompat.getColor(mContext,R.color.colorAccent)
        );
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh.setRefreshing(false);
                    }
                },3000);
            }
        });
    }

    @Override
    protected void initData() {
        sp = mContext.getSharedPreferences("config", Context.MODE_PRIVATE);
        final MyAdapter myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(mContext, myAdapter.getItem(position).getType(),Toast.LENGTH_SHORT).show();
            }
        });
        RApiService rApiService = RRetrofit.create(RApiService.class);
        Observable<Message> securityCode = rApiService.getJson();
        securityCode.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Message>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Message value) {
                if (value != null && value.getData() != null) {
                    String str ="{\n" +
                            "    \"data\": {\n" +
                            "        \"carousel\": [\n" +
                            "            {\n" +
                            "                \"id\": 2,\n" +
                            "                \"imgUrl\": \"https://storage.moneng.cn/moral/carousel/I105873hj5gggk3839164858_zcvefh.png\",\n" +
                            "                \"title\": \"摩能国际总裁万兵受邀参加《超越》\",\n" +
                            "                \"toId\": 8,\n" +
                            "                \"type\": \"IMAGE_TEXT\",\n" +
                            "                \"webUrl\": \"https://server.moneng.cn/h5/info/article.htm?id=8\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"id\": 3,\n" +
                            "                \"imgUrl\": \"https://storage.moneng.cn/moral/carousel/I105873hj5hkip1172068357_ubIjga.png\",\n" +
                            "                \"title\": \"摩能国际高层研讨会\",\n" +
                            "                \"toId\": 146,\n" +
                            "                \"type\": \"VIDEO\",\n" +
                            "                \"webUrl\": \"https://storage.moneng.cn/moral/article/I105873jhm1k93782996416_ncowpK.mp4\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"id\": 4,\n" +
                            "                \"imgUrl\": \"https://storage.moneng.cn/moral/carousel/I105873hj5i4ag1195135218_mjmyHh.png\",\n" +
                            "                \"title\": \"千亿战略，时代企业家\",\n" +
                            "                \"toId\": 154,\n" +
                            "                \"type\": \"VIDEO\",\n" +
                            "                \"webUrl\": \"https://storage.moneng.cn/moral/article/I105873l3kgo6j183960879_uXYpmE.mp4\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"id\": 7,\n" +
                            "                \"imgUrl\": \"https://storage.moneng.cn/moral/carousel/I105873jjnafio2773850445_yPvikR.png\",\n" +
                            "                \"title\": \"梦想起航，再创辉煌\",\n" +
                            "                \"toId\": 133,\n" +
                            "                \"type\": \"IMAGE_TEXT\",\n" +
                            "                \"webUrl\": \"https://server.moneng.cn/h5/info/article.htm?id=133\"\n" +
                            "            }\n" +
                            "        ],\n" +
                            "        \"content\": [\n" +
                            "            {\n" +
                            "                \"commentNum\": 0,\n" +
                            "                \"imgSize\": 3,\n" +
                            "                \"imgUrl\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493560841819&di=2037183af96347668374631cc514fdf2&imgtype=0&src=http%3A%2F%2Fmvimg1.meitudata.com%2F56ce93c1ceccc1187.jpg\",\n" +
                            "                \"time\": 1492583636000,\n" +
                            "                \"title\": \"牛蒡茶火爆微商博览会 成为微商界最强“黑马”\",\n" +
                            "                \"toId\": 152,\n" +
                            "                \"type\": \"IMAGE_TEXT\",\n" +
                            "                \"watchNum\": 16,\n" +
                            "                \"webUrl\": \"https://server.moneng.cn/h5/info/article.htm?id=152\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"commentNum\": 7,\n" +
                            "                \"imgSize\": 3,\n" +
                            "                \"imgUrl\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493560841818&di=ff73df3147976b882382464cb5b094c5&imgtype=0&src=http%3A%2F%2Fmvimg2.meitudata.com%2F55d5b36c1e4e899.jpg\",\n" +
                            "                \"time\": 1492500101000,\n" +
                            "                \"title\": \"千亿战略，时代企业家\",\n" +
                            "                \"toId\": 8,\n" +
                            "                \"type\": \"IMAGE_TEXT_2\",\n" +
                            "                \"watchNum\": 25,\n" +
                            "                \"webUrl\": \"https://server.moneng.cn/h5/info/article.htm?id=8\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"commentNum\": 0,\n" +
                            "                \"imgSize\": 3,\n" +
                            "                \"imgUrl\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493560841818&di=d25f83de9e8812086119be305fb9a24c&imgtype=0&src=http%3A%2F%2Fmvimg2.meitudata.com%2F56c74ca3907f66153.jpg\",\n" +
                            "                \"time\": 1491975670000,\n" +
                            "                \"title\": \"诺基亚3310回归！主打彩壳科技以换壳为本\",\n" +
                            "                \"toId\": 56,\n" +
                            "                \"type\": \"IMAGE_TEXT\",\n" +
                            "                \"watchNum\": 3,\n" +
                            "                \"webUrl\": \"https://server.moneng.cn/h5/info/article.htm?id=56\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"commentNum\": 0,\n" +
                            "                \"imgSize\": 3,\n" +
                            "                \"imgUrl\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493560841818&di=b5437406d865572993e43231b3f93968&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fphotoblog%2F1112%2F29%2Fc2%2F10087294_10087294_1325133605031_mthumb.jpg\",\n" +
                            "                \"time\": 1488175420000,\n" +
                            "                \"title\": \"华为发布P10：前置镜头也徕卡 绿色太骚了\",\n" +
                            "                \"toId\": 9,\n" +
                            "                \"type\": \"IMAGE_TEXT_2\",\n" +
                            "                \"watchNum\": 0,\n" +
                            "                \"webUrl\": \"https://server.moneng.cn/h5/info/article.htm?id=9\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"commentNum\": 0,\n" +
                            "                \"imgSize\": 3,\n" +
                            "                \"imgUrl\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493560841817&di=88a06436076bf134ff9fb31f2f78b9ba&imgtype=0&src=http%3A%2F%2Fimage142-c.poco.cn%2Fmypoco%2Fmyphoto%2F20130713%2F21%2F659346182013071321162601.jpg%3F900x600_120\",\n" +
                            "                \"time\": 1488168000000,\n" +
                            "                \"title\": \"设计师帮苹果设计的概念跑车 还是自动驾驶\",\n" +
                            "                \"toId\": 4,\n" +
                            "                \"type\": \"IMAGE_TEXT\",\n" +
                            "                \"watchNum\": 0,\n" +
                            "                \"webUrl\": \"https://server.moneng.cn/h5/info/article.htm?id=4\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"commentNum\": 0,\n" +
                            "                \"imgSize\": 3,\n" +
                            "                \"imgUrl\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493560841817&di=88a06436076bf134ff9fb31f2f78b9ba&imgtype=0&src=http%3A%2F%2Fimage142-c.poco.cn%2Fmypoco%2Fmyphoto%2F20130713%2F21%2F659346182013071321162601.jpg%3F900x600_120\",\n" +
                            "                \"time\": 1488167530000,\n" +
                            "                \"title\": \"Exynos 8895对比骁龙835 哪个版本三星S8更强？\",\n" +
                            "                \"toId\": 2,\n" +
                            "                \"type\": \"IMAGE_TEXT_2\",\n" +
                            "                \"watchNum\": 0,\n" +
                            "                \"webUrl\": \"https://server.moneng.cn/h5/info/article.htm?id=2\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"commentNum\": 0,\n" +
                            "                \"imgSize\": 3,\n" +
                            "                \"imgUrl\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493560841817&di=88a06436076bf134ff9fb31f2f78b9ba&imgtype=0&src=http%3A%2F%2Fimage142-c.poco.cn%2Fmypoco%2Fmyphoto%2F20130713%2F21%2F659346182013071321162601.jpg%3F900x600_120\",\n" +
                            "                \"time\": 1487932456000,\n" +
                            "                \"title\": \"好惨！在与苹果的竞争中三星已没有还手之力\",\n" +
                            "                \"toId\": 12,\n" +
                            "                \"type\": \"IMAGE_TEXT\",\n" +
                            "                \"watchNum\": 0,\n" +
                            "                \"webUrl\": \"https://server.moneng.cn/h5/info/article.htm?id=12\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"commentNum\": 0,\n" +
                            "                \"imgSize\": 3,\n" +
                            "                \"imgUrl\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493560841817&di=88a06436076bf134ff9fb31f2f78b9ba&imgtype=0&src=http%3A%2F%2Fimage142-c.poco.cn%2Fmypoco%2Fmyphoto%2F20130713%2F21%2F659346182013071321162601.jpg%3F900x600_120\",\n" +
                            "                \"time\": 1487932434000,\n" +
                            "                \"title\": \"诺基亚3310回归！主打彩壳科技以换壳为本\",\n" +
                            "                \"toId\": 60,\n" +
                            "                \"type\": \"IMAGE_TEXT_2\",\n" +
                            "                \"watchNum\": 5,\n" +
                            "                \"webUrl\": \"https://server.moneng.cn/h5/info/article.htm?id=60\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"commentNum\": 0,\n" +
                            "                \"imgSize\": 3,\n" +
                            "                \"imgUrl\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493560841817&di=88a06436076bf134ff9fb31f2f78b9ba&imgtype=0&src=http%3A%2F%2Fimage142-c.poco.cn%2Fmypoco%2Fmyphoto%2F20130713%2F21%2F659346182013071321162601.jpg%3F900x600_120\",\n" +
                            "                \"time\": 1487932432000,\n" +
                            "                \"title\": \"诺基亚3310回归！主打彩壳科技以换壳为本\",\n" +
                            "                \"toId\": 59,\n" +
                            "                \"type\": \"IMAGE_TEXT_2\",\n" +
                            "                \"watchNum\": 2,\n" +
                            "                \"webUrl\": \"https://server.moneng.cn/h5/info/article.htm?id=59\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"commentNum\": 0,\n" +
                            "                \"imgSize\": 3,\n" +
                            "                \"imgUrl\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493560841817&di=88a06436076bf134ff9fb31f2f78b9ba&imgtype=0&src=http%3A%2F%2Fimage142-c.poco.cn%2Fmypoco%2Fmyphoto%2F20130713%2F21%2F659346182013071321162601.jpg%3F900x600_120\",\n" +
                            "                \"time\": 1487932430000,\n" +
                            "                \"title\": \"诺基亚3310回归！主打彩壳科技以换壳为本\",\n" +
                            "                \"toId\": 58,\n" +
                            "                \"type\": \"IMAGE_TEXT\",\n" +
                            "                \"watchNum\": 1,\n" +
                            "                \"webUrl\": \"https://server.moneng.cn/h5/info/article.htm?id=58\"\n" +
                            "            }\n" +
                            "        ]\n" +
                            "    },\n" +
                            "    \"errorCode\": 0,\n" +
                            "    \"errorMsg\": \"\",\n" +
                            "    \"page\": 1,\n" +
                            "    \"result\": \"success\",\n" +
                            "    \"total\": 35\n" +
                            "}";
                    sp.edit().putString("json",str).commit();
                    myAdapter.setData(value.getData().getContent());
                }
            }

            @Override
            public void onError(Throwable e) {
                String json = sp.getString("json","");
                Gson gson = new Gson();
                Message message = gson.fromJson(json, Message.class);
                myAdapter.setData(message.getData().getContent());
            }

            @Override
            public void onComplete() {

            }
        });

    }
    interface OnItemClickListener {
        public void onItemClick(int position);
    }
    private class MyAdapter extends RecyclerView.Adapter {
         private OnItemClickListener onItemClickListener;
        public void setOnItemClickListener(OnItemClickListener onItemClickListener){
            this.onItemClickListener=onItemClickListener;
        }
        List<Message.DataBean.ContentBean> content;
        private static final String TYPE_ONE = "IMAGE_TEXT";
        private static final String TYPE_TWO = "IMAGE_TEXT_2";

        public void setData(List<Message.DataBean.ContentBean> content) {
            this.content = content;
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder viewHolder = null;
            if (viewType == 0) {
                viewHolder = new ViewHolder1(mInflater.inflate(R.layout.item_message, parent, false));
            } else if (viewType == 1) {
                viewHolder = new ViewHolder2(mInflater.inflate(R.layout.item_message2, parent, false));
            }
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            Message.DataBean.ContentBean contentBean = content.get(position);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if (getItemViewType(position) == 0) {
                ViewHolder1 viewHolder1 = (ViewHolder1) holder;
                viewHolder1.llContent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(onItemClickListener!=null){
                            onItemClickListener.onItemClick(position);
                        }
                    }
                });
                Glide.with(mContext).load(contentBean.getImgUrl()).into(viewHolder1.iv);
                viewHolder1.tvWatch.setText(String.valueOf(contentBean.getWatchNum()));
                viewHolder1.tvComment.setText(String.valueOf(contentBean.getCommentNum()));
                viewHolder1.tvTitle.setText(contentBean.getTitle());
                viewHolder1.tvDate.setText(format.format(new Date(contentBean.getTime())));
            } else if (getItemViewType(position) == 1) {
                ViewHolder2 viewHolder2 = (ViewHolder2) holder;
                viewHolder2.llContent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(onItemClickListener!=null){
                            onItemClickListener.onItemClick(position);
                        }
                    }
                });
                Glide.with(mContext).load(contentBean.getImgUrl()).into(viewHolder2.iv);;
                viewHolder2.tvWatch.setText(String.valueOf(contentBean.getWatchNum()));
                viewHolder2.tvComment.setText(String.valueOf(contentBean.getCommentNum()));
                viewHolder2.tvTitle.setText(contentBean.getTitle());
                viewHolder2.tvDate.setText(format.format(new Date(contentBean.getTime())));
            }
        }
        public Message.DataBean.ContentBean getItem(int position){
            return content.get(position);
        }
        @Override
        public int getItemCount() {
            return content == null ? 0 : content.size();
        }

        @Override
        public int getItemViewType(int position) {
            return content.get(position).getType().equals(TYPE_ONE) ? 0 : 1;
        }
    }

     class ViewHolder1 extends RecyclerView.ViewHolder {
         @Bind(R.id.ll_content)
         LinearLayout llContent;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.iv)
        ImageView iv;
        @Bind(R.id.tv_date)
        TextView tvDate;
        @Bind(R.id.tv_comment)
        TextView tvComment;
        @Bind(R.id.tv_watch)
        TextView tvWatch;

        public ViewHolder1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

     class ViewHolder2 extends RecyclerView.ViewHolder {
         @Bind(R.id.ll_content)
         LinearLayout llContent;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_date)
        TextView tvDate;
        @Bind(R.id.tv_comment)
        TextView tvComment;
        @Bind(R.id.tv_watch)
        TextView tvWatch;
        @Bind(R.id.iv)
        ImageView iv;
        public ViewHolder2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
