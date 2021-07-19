// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MyTrackerSDK.proto

package tech.ubic.ed.mycomproxy.proto;

public interface MyTrackerSDKOrBuilder extends
    // @@protoc_insertion_point(interface_extends:MyTrackerSDK)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * (required) версия myTracker SDK (tag &lt; 15)
   * </pre>
   *
   * <code>optional bytes mytracker_version = 1;</code>
   * @return Whether the mytrackerVersion field is set.
   */
  boolean hasMytrackerVersion();
  /**
   * <pre>
   * (required) версия myTracker SDK (tag &lt; 15)
   * </pre>
   *
   * <code>optional bytes mytracker_version = 1;</code>
   * @return The mytrackerVersion.
   */
  com.google.protobuf.ByteString getMytrackerVersion();

  /**
   * <pre>
   * (required) id приложения выданный в РБ
   * </pre>
   *
   * <code>optional bytes app_id = 2;</code>
   * @return Whether the appId field is set.
   */
  boolean hasAppId();
  /**
   * <pre>
   * (required) id приложения выданный в РБ
   * </pre>
   *
   * <code>optional bytes app_id = 2;</code>
   * @return The appId.
   */
  com.google.protobuf.ByteString getAppId();

  /**
   * <pre>
   * (required) базовый таймштамп (unixtime в целых секундах от начала эпохи), от которого считаются смещения для событий
   * </pre>
   *
   * <code>optional uint64 timestamp_base = 3;</code>
   * @return Whether the timestampBase field is set.
   */
  boolean hasTimestampBase();
  /**
   * <pre>
   * (required) базовый таймштамп (unixtime в целых секундах от начала эпохи), от которого считаются смещения для событий
   * </pre>
   *
   * <code>optional uint64 timestamp_base = 3;</code>
   * @return The timestampBase.
   */
  long getTimestampBase();

  /**
   * <pre>
   * (required) таймштамп времени отправки
   * </pre>
   *
   * <code>optional uint64 timestamp_send = 4;</code>
   * @return Whether the timestampSend field is set.
   */
  boolean hasTimestampSend();
  /**
   * <pre>
   * (required) таймштамп времени отправки
   * </pre>
   *
   * <code>optional uint64 timestamp_send = 4;</code>
   * @return The timestampSend.
   */
  long getTimestampSend();

  /**
   * <pre>
   * (optional) кол-во событий которое было потеряно на клиенте из-за переполнения (кеш хранит N уникальных событий пришедших первыми)
   * </pre>
   *
   * <code>optional uint32 custom_events_skipped = 5;</code>
   * @return Whether the customEventsSkipped field is set.
   */
  boolean hasCustomEventsSkipped();
  /**
   * <pre>
   * (optional) кол-во событий которое было потеряно на клиенте из-за переполнения (кеш хранит N уникальных событий пришедших первыми)
   * </pre>
   *
   * <code>optional uint32 custom_events_skipped = 5;</code>
   * @return The customEventsSkipped.
   */
  int getCustomEventsSkipped();

  /**
   * <pre>
   * (required) SHA256 ключ для MyTrackerSDK message (формируется после создания message и записывается в конец потока байт)
   * </pre>
   *
   * <code>optional bytes sha256_hash = 6;</code>
   * @return Whether the sha256Hash field is set.
   */
  boolean hasSha256Hash();
  /**
   * <pre>
   * (required) SHA256 ключ для MyTrackerSDK message (формируется после создания message и записывается в конец потока байт)
   * </pre>
   *
   * <code>optional bytes sha256_hash = 6;</code>
   * @return The sha256Hash.
   */
  com.google.protobuf.ByteString getSha256Hash();

  /**
   * <pre>
   * added by rcvd.
   * </pre>
   *
   * <code>repeated fixed32 src_ip_chain = 14;</code>
   * @return A list containing the srcIpChain.
   */
  java.util.List<java.lang.Integer> getSrcIpChainList();
  /**
   * <pre>
   * added by rcvd.
   * </pre>
   *
   * <code>repeated fixed32 src_ip_chain = 14;</code>
   * @return The count of srcIpChain.
   */
  int getSrcIpChainCount();
  /**
   * <pre>
   * added by rcvd.
   * </pre>
   *
   * <code>repeated fixed32 src_ip_chain = 14;</code>
   * @param index The index of the element to return.
   * @return The srcIpChain at the given index.
   */
  int getSrcIpChain(int index);

  /**
   * <pre>
   * added by rcvd.
   * </pre>
   *
   * <code>optional fixed32 timestamp = 15;</code>
   * @return Whether the timestamp field is set.
   */
  boolean hasTimestamp();
  /**
   * <pre>
   * added by rcvd.
   * </pre>
   *
   * <code>optional fixed32 timestamp = 15;</code>
   * @return The timestamp.
   */
  int getTimestamp();

  /**
   * <pre>
   * (required) информация о приложении
   * </pre>
   *
   * <code>optional .MyTrackerSDK.AppInfo app_info = 21;</code>
   * @return Whether the appInfo field is set.
   */
  boolean hasAppInfo();
  /**
   * <pre>
   * (required) информация о приложении
   * </pre>
   *
   * <code>optional .MyTrackerSDK.AppInfo app_info = 21;</code>
   * @return The appInfo.
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.AppInfo getAppInfo();
  /**
   * <pre>
   * (required) информация о приложении
   * </pre>
   *
   * <code>optional .MyTrackerSDK.AppInfo app_info = 21;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.AppInfoOrBuilder getAppInfoOrBuilder();

  /**
   * <pre>
   * (optional) информация о пользователе
   * </pre>
   *
   * <code>optional .MyTrackerSDK.UserInfo user_info = 22;</code>
   * @return Whether the userInfo field is set.
   */
  boolean hasUserInfo();
  /**
   * <pre>
   * (optional) информация о пользователе
   * </pre>
   *
   * <code>optional .MyTrackerSDK.UserInfo user_info = 22;</code>
   * @return The userInfo.
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.UserInfo getUserInfo();
  /**
   * <pre>
   * (optional) информация о пользователе
   * </pre>
   *
   * <code>optional .MyTrackerSDK.UserInfo user_info = 22;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.UserInfoOrBuilder getUserInfoOrBuilder();

  /**
   * <pre>
   * (required) информация об устройстве
   * </pre>
   *
   * <code>optional .MyTrackerSDK.DeviceInfo device_info = 23;</code>
   * @return Whether the deviceInfo field is set.
   */
  boolean hasDeviceInfo();
  /**
   * <pre>
   * (required) информация об устройстве
   * </pre>
   *
   * <code>optional .MyTrackerSDK.DeviceInfo device_info = 23;</code>
   * @return The deviceInfo.
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.DeviceInfo getDeviceInfo();
  /**
   * <pre>
   * (required) информация об устройстве
   * </pre>
   *
   * <code>optional .MyTrackerSDK.DeviceInfo device_info = 23;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.DeviceInfoOrBuilder getDeviceInfoOrBuilder();

  /**
   * <pre>
   * (optional) МРГС информация
   * </pre>
   *
   * <code>optional .MyTrackerSDK.MrgsInfo mrgs_info = 24;</code>
   * @return Whether the mrgsInfo field is set.
   */
  boolean hasMrgsInfo();
  /**
   * <pre>
   * (optional) МРГС информация
   * </pre>
   *
   * <code>optional .MyTrackerSDK.MrgsInfo mrgs_info = 24;</code>
   * @return The mrgsInfo.
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.MrgsInfo getMrgsInfo();
  /**
   * <pre>
   * (optional) МРГС информация
   * </pre>
   *
   * <code>optional .MyTrackerSDK.MrgsInfo mrgs_info = 24;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.MrgsInfoOrBuilder getMrgsInfoOrBuilder();

  /**
   * <pre>
   * (required) информация о сети
   * </pre>
   *
   * <code>optional .MyTrackerSDK.NetworkInfo network_info = 25;</code>
   * @return Whether the networkInfo field is set.
   */
  boolean hasNetworkInfo();
  /**
   * <pre>
   * (required) информация о сети
   * </pre>
   *
   * <code>optional .MyTrackerSDK.NetworkInfo network_info = 25;</code>
   * @return The networkInfo.
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.NetworkInfo getNetworkInfo();
  /**
   * <pre>
   * (required) информация о сети
   * </pre>
   *
   * <code>optional .MyTrackerSDK.NetworkInfo network_info = 25;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.NetworkInfoOrBuilder getNetworkInfoOrBuilder();

  /**
   * <pre>
   * (optional) информация о местоположении, отправляется если есть доступ
   * </pre>
   *
   * <code>optional .MyTrackerSDK.LocationInfo location_info = 26;</code>
   * @return Whether the locationInfo field is set.
   */
  boolean hasLocationInfo();
  /**
   * <pre>
   * (optional) информация о местоположении, отправляется если есть доступ
   * </pre>
   *
   * <code>optional .MyTrackerSDK.LocationInfo location_info = 26;</code>
   * @return The locationInfo.
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.LocationInfo getLocationInfo();
  /**
   * <pre>
   * (optional) информация о местоположении, отправляется если есть доступ
   * </pre>
   *
   * <code>optional .MyTrackerSDK.LocationInfo location_info = 26;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.LocationInfoOrBuilder getLocationInfoOrBuilder();

  /**
   * <pre>
   * (optional) объект настроек, присутствует если настроки отличаются от дефолтных
   * </pre>
   *
   * <code>optional .MyTrackerSDK.Settings settings = 27;</code>
   * @return Whether the settings field is set.
   */
  boolean hasSettings();
  /**
   * <pre>
   * (optional) объект настроек, присутствует если настроки отличаются от дефолтных
   * </pre>
   *
   * <code>optional .MyTrackerSDK.Settings settings = 27;</code>
   * @return The settings.
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.Settings getSettings();
  /**
   * <pre>
   * (optional) объект настроек, присутствует если настроки отличаются от дефолтных
   * </pre>
   *
   * <code>optional .MyTrackerSDK.Settings settings = 27;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.SettingsOrBuilder getSettingsOrBuilder();

  /**
   * <pre>
   * (optional) информация о текущем Wi-Fi соединении
   * </pre>
   *
   * <code>optional .MyTrackerSDK.WifiInfo wifi_current = 28;</code>
   * @return Whether the wifiCurrent field is set.
   */
  boolean hasWifiCurrent();
  /**
   * <pre>
   * (optional) информация о текущем Wi-Fi соединении
   * </pre>
   *
   * <code>optional .MyTrackerSDK.WifiInfo wifi_current = 28;</code>
   * @return The wifiCurrent.
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.WifiInfo getWifiCurrent();
  /**
   * <pre>
   * (optional) информация о текущем Wi-Fi соединении
   * </pre>
   *
   * <code>optional .MyTrackerSDK.WifiInfo wifi_current = 28;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.WifiInfoOrBuilder getWifiCurrentOrBuilder();

  /**
   * <pre>
   * (optional) (Android only) информация о доступных Wi-Fi сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.WifiInfo wifi_around = 29;</code>
   */
  java.util.List<tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.WifiInfo> 
      getWifiAroundList();
  /**
   * <pre>
   * (optional) (Android only) информация о доступных Wi-Fi сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.WifiInfo wifi_around = 29;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.WifiInfo getWifiAround(int index);
  /**
   * <pre>
   * (optional) (Android only) информация о доступных Wi-Fi сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.WifiInfo wifi_around = 29;</code>
   */
  int getWifiAroundCount();
  /**
   * <pre>
   * (optional) (Android only) информация о доступных Wi-Fi сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.WifiInfo wifi_around = 29;</code>
   */
  java.util.List<? extends tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.WifiInfoOrBuilder> 
      getWifiAroundOrBuilderList();
  /**
   * <pre>
   * (optional) (Android only) информация о доступных Wi-Fi сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.WifiInfo wifi_around = 29;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.WifiInfoOrBuilder getWifiAroundOrBuilder(
      int index);

  /**
   * <pre>
   * (optional) (Android only) (deprecated) информация с сотовых сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfo cell = 30;</code>
   */
  java.util.List<tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfo> 
      getCellList();
  /**
   * <pre>
   * (optional) (Android only) (deprecated) информация с сотовых сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfo cell = 30;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfo getCell(int index);
  /**
   * <pre>
   * (optional) (Android only) (deprecated) информация с сотовых сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfo cell = 30;</code>
   */
  int getCellCount();
  /**
   * <pre>
   * (optional) (Android only) (deprecated) информация с сотовых сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfo cell = 30;</code>
   */
  java.util.List<? extends tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoOrBuilder> 
      getCellOrBuilderList();
  /**
   * <pre>
   * (optional) (Android only) (deprecated) информация с сотовых сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfo cell = 30;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoOrBuilder getCellOrBuilder(
      int index);

  /**
   * <pre>
   * (optional) (Android only) информация об установленных приложениях, отправляется только при изменении списка
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.PackageInfo package = 31;</code>
   */
  java.util.List<tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.PackageInfo> 
      getPackageList();
  /**
   * <pre>
   * (optional) (Android only) информация об установленных приложениях, отправляется только при изменении списка
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.PackageInfo package = 31;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.PackageInfo getPackage(int index);
  /**
   * <pre>
   * (optional) (Android only) информация об установленных приложениях, отправляется только при изменении списка
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.PackageInfo package = 31;</code>
   */
  int getPackageCount();
  /**
   * <pre>
   * (optional) (Android only) информация об установленных приложениях, отправляется только при изменении списка
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.PackageInfo package = 31;</code>
   */
  java.util.List<? extends tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.PackageInfoOrBuilder> 
      getPackageOrBuilderList();
  /**
   * <pre>
   * (optional) (Android only) информация об установленных приложениях, отправляется только при изменении списка
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.PackageInfo package = 31;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.PackageInfoOrBuilder getPackageOrBuilder(
      int index);

  /**
   * <pre>
   * (optional) (Android only) информация о cdma сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoCdma cdma_cell = 32;</code>
   */
  java.util.List<tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoCdma> 
      getCdmaCellList();
  /**
   * <pre>
   * (optional) (Android only) информация о cdma сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoCdma cdma_cell = 32;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoCdma getCdmaCell(int index);
  /**
   * <pre>
   * (optional) (Android only) информация о cdma сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoCdma cdma_cell = 32;</code>
   */
  int getCdmaCellCount();
  /**
   * <pre>
   * (optional) (Android only) информация о cdma сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoCdma cdma_cell = 32;</code>
   */
  java.util.List<? extends tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoCdmaOrBuilder> 
      getCdmaCellOrBuilderList();
  /**
   * <pre>
   * (optional) (Android only) информация о cdma сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoCdma cdma_cell = 32;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoCdmaOrBuilder getCdmaCellOrBuilder(
      int index);

  /**
   * <pre>
   * (optional) (Android only) информация о gsm сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoGsm gsm_cell = 33;</code>
   */
  java.util.List<tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoGsm> 
      getGsmCellList();
  /**
   * <pre>
   * (optional) (Android only) информация о gsm сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoGsm gsm_cell = 33;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoGsm getGsmCell(int index);
  /**
   * <pre>
   * (optional) (Android only) информация о gsm сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoGsm gsm_cell = 33;</code>
   */
  int getGsmCellCount();
  /**
   * <pre>
   * (optional) (Android only) информация о gsm сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoGsm gsm_cell = 33;</code>
   */
  java.util.List<? extends tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoGsmOrBuilder> 
      getGsmCellOrBuilderList();
  /**
   * <pre>
   * (optional) (Android only) информация о gsm сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoGsm gsm_cell = 33;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoGsmOrBuilder getGsmCellOrBuilder(
      int index);

  /**
   * <pre>
   * (optional) (Android only) информация о lte сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoLte lte_cell = 34;</code>
   */
  java.util.List<tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoLte> 
      getLteCellList();
  /**
   * <pre>
   * (optional) (Android only) информация о lte сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoLte lte_cell = 34;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoLte getLteCell(int index);
  /**
   * <pre>
   * (optional) (Android only) информация о lte сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoLte lte_cell = 34;</code>
   */
  int getLteCellCount();
  /**
   * <pre>
   * (optional) (Android only) информация о lte сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoLte lte_cell = 34;</code>
   */
  java.util.List<? extends tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoLteOrBuilder> 
      getLteCellOrBuilderList();
  /**
   * <pre>
   * (optional) (Android only) информация о lte сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoLte lte_cell = 34;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoLteOrBuilder getLteCellOrBuilder(
      int index);

  /**
   * <pre>
   * (optional) (Android only) информация о wcdma сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoWcdma wcdma_cell = 35;</code>
   */
  java.util.List<tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoWcdma> 
      getWcdmaCellList();
  /**
   * <pre>
   * (optional) (Android only) информация о wcdma сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoWcdma wcdma_cell = 35;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoWcdma getWcdmaCell(int index);
  /**
   * <pre>
   * (optional) (Android only) информация о wcdma сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoWcdma wcdma_cell = 35;</code>
   */
  int getWcdmaCellCount();
  /**
   * <pre>
   * (optional) (Android only) информация о wcdma сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoWcdma wcdma_cell = 35;</code>
   */
  java.util.List<? extends tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoWcdmaOrBuilder> 
      getWcdmaCellOrBuilderList();
  /**
   * <pre>
   * (optional) (Android only) информация о wcdma сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoWcdma wcdma_cell = 35;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoWcdmaOrBuilder getWcdmaCellOrBuilder(
      int index);

  /**
   * <pre>
   * (optional) (Android only) информация о tdscdma сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoTdscdma tdscdma_cell = 36;</code>
   */
  java.util.List<tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoTdscdma> 
      getTdscdmaCellList();
  /**
   * <pre>
   * (optional) (Android only) информация о tdscdma сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoTdscdma tdscdma_cell = 36;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoTdscdma getTdscdmaCell(int index);
  /**
   * <pre>
   * (optional) (Android only) информация о tdscdma сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoTdscdma tdscdma_cell = 36;</code>
   */
  int getTdscdmaCellCount();
  /**
   * <pre>
   * (optional) (Android only) информация о tdscdma сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoTdscdma tdscdma_cell = 36;</code>
   */
  java.util.List<? extends tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoTdscdmaOrBuilder> 
      getTdscdmaCellOrBuilderList();
  /**
   * <pre>
   * (optional) (Android only) информация о tdscdma сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoTdscdma tdscdma_cell = 36;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoTdscdmaOrBuilder getTdscdmaCellOrBuilder(
      int index);

  /**
   * <pre>
   * (optional) (Android only) информация о nr сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoNr nr_cell = 37;</code>
   */
  java.util.List<tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoNr> 
      getNrCellList();
  /**
   * <pre>
   * (optional) (Android only) информация о nr сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoNr nr_cell = 37;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoNr getNrCell(int index);
  /**
   * <pre>
   * (optional) (Android only) информация о nr сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoNr nr_cell = 37;</code>
   */
  int getNrCellCount();
  /**
   * <pre>
   * (optional) (Android only) информация о nr сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoNr nr_cell = 37;</code>
   */
  java.util.List<? extends tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoNrOrBuilder> 
      getNrCellOrBuilderList();
  /**
   * <pre>
   * (optional) (Android only) информация о nr сетях
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.CellInfoNr nr_cell = 37;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.CellInfoNrOrBuilder getNrCellOrBuilder(
      int index);

  /**
   * <pre>
   * (optional) события
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.Event event = 41;</code>
   */
  java.util.List<tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.Event> 
      getEventList();
  /**
   * <pre>
   * (optional) события
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.Event event = 41;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.Event getEvent(int index);
  /**
   * <pre>
   * (optional) события
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.Event event = 41;</code>
   */
  int getEventCount();
  /**
   * <pre>
   * (optional) события
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.Event event = 41;</code>
   */
  java.util.List<? extends tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.EventOrBuilder> 
      getEventOrBuilderList();
  /**
   * <pre>
   * (optional) события
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.Event event = 41;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.EventOrBuilder getEventOrBuilder(
      int index);

  /**
   * <pre>
   * (optional) сессии
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.Session session = 42;</code>
   */
  java.util.List<tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.Session> 
      getSessionList();
  /**
   * <pre>
   * (optional) сессии
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.Session session = 42;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.Session getSession(int index);
  /**
   * <pre>
   * (optional) сессии
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.Session session = 42;</code>
   */
  int getSessionCount();
  /**
   * <pre>
   * (optional) сессии
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.Session session = 42;</code>
   */
  java.util.List<? extends tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.SessionOrBuilder> 
      getSessionOrBuilderList();
  /**
   * <pre>
   * (optional) сессии
   * </pre>
   *
   * <code>repeated .MyTrackerSDK.Session session = 42;</code>
   */
  tech.ubic.ed.mycomproxy.proto.MyTrackerSDK.SessionOrBuilder getSessionOrBuilder(
      int index);
}
