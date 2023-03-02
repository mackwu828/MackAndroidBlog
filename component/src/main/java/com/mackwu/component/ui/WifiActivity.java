package com.mackwu.component.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.ActivityStartUtil;
import com.mackwu.base.util.Logger;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.databinding.WifiActivityBinding;
import com.mackwu.component.util.PermissionUtil;
import com.mackwu.component.func.wifi.WifiUtil;
import com.thanosfisherman.wifiutils.WifiUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2020/12/29 13:59
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * android10连接上wifi后，只有自身的app有网络，其他app如浏览器显示无网络？
 * https://stackoverflow.com/questions/57929863/android-q-10-connect-to-network-wifinetworkspecifier
 * https://stackoverflow.com/questions/56831023/android-q-programmatically-connect-to-different-wifi-ap-for-internet
 * <p>
 * wifi库：
 * https://github.com/ThanosFisherman/WifiUtils
 * https://github.com/ThanosFisherman/WifiUtils/issues/47
 */
public class WifiActivity extends BaseActivity<BaseViewModel, WifiActivityBinding> {

    private WifiAdapter adapter;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        // adapter
        adapter = new WifiAdapter();
        adapter.setOnItemClickListener((a, view, position) -> {
            String ssid = adapter.getItem(position);
            Logger.d("connect...  ssid: " + ssid);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
//                WifiNetworkSuggestion wifiNetworkSuggestion = new WifiNetworkSuggestion.Builder()
//                        .setSsid(ssid)
//                        .setWpa2Passphrase("zeasnandroid")
//                        .build();
//
//                ArrayList<WifiNetworkSuggestion> wifiNetworkSuggestions = new ArrayList<>();
//                wifiNetworkSuggestions.add(wifiNetworkSuggestion);
//
//                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//                int status = wifiManager.addNetworkSuggestions(wifiNetworkSuggestions);
//                if (status != WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS) {
//
//                }

//        WifiNetworkSpecifier wifiNetworkSpecifier = new WifiNetworkSpecifier.Builder()
//                .setSsid(ssid)
//                .setWpa2Passphrase("zeasnandroid")
//                .build();
//
//        NetworkRequest networkRequest = new NetworkRequest.Builder()
//                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
//                .setNetworkSpecifier(wifiNetworkSpecifier)
//                .build();
//
//        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        connectivityManager.requestNetwork(networkRequest, new ConnectivityManager.NetworkCallback() {
//            @Override
//            public void onAvailable(@NonNull Network network) {
//                super.onAvailable(network);
//                LogUtil.d("onAvailable...");
//                connectivityManager.bindProcessToNetwork(network);
//                LogUtil.d("isNetworkAvailable: " + NetworkUtil.isNetworkAvailable(WifiActivity.this));
//            }
//        });

//                WifiUtils.withContext(getApplicationContext())
//                        .connectWith(ssid, "")
//                        .setTimeout(30 * 1000)
//                        .onConnectionResult(new ConnectionSuccessListener() {
//                            @Override
//                            public void success() {
//                                LogUtil.d("success...  isNetworkAvailable: " + NetworkUtil.isNetworkAvailable(WifiActivity.this));
//                                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//                                LogUtil.d("isAlreadyConnected: " + ConnectorUtils.isAlreadyConnected(wifiManager, "40:31:3c:03:41:ca"));
//                            }
//
//                            @Override
//                            public void failed(@NonNull ConnectionErrorCode errorCode) {
//                                LogUtil.d("failed...  errorCode: " + errorCode);
//                            }
//                        })
//                        .start();

                WifiUtil.connectWifi(this, ssid, "zeasnandroid");
            }
        });

        // recyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(adapter);

        binding.btnTest.setOnClickListener(v -> {
            ActivityStartUtil.startActivity(this, WebActivity.class);
        });

        PermissionUtil.requestPermission(this, Manifest.permission.ACCESS_FINE_LOCATION, granted -> {
            Logger.d("ACCESS_FINE_LOCATION permission granted...");
            if (!granted) return;
            WifiUtils.withContext(getApplicationContext())
                    .scanWifi(scanResults -> {
                        Logger.d("onScanResults...  " + scanResults.size());
                        List<String> wifiItems = new ArrayList<>();
                        for (ScanResult scanResult : scanResults) {
                            Logger.d("ssid:" + scanResult.SSID + ", bssid: " + scanResult.BSSID);
                            wifiItems.add(scanResult.SSID);
                        }
                        adapter.setNewData(wifiItems);
                    })
                    .start();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {

            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }

    static class WifiAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public WifiAdapter() {
            super(R.layout.wifi_item);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder helper, String s) {
            helper.setText(R.id.tv_wifi_name, s);
        }
    }

}
