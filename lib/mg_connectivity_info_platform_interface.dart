import 'package:mg_connectivity_info/mg_connectivity_info_common.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'mg_connectivity_info_method_channel.dart';

abstract class MgConnectivityInfoPlatform extends PlatformInterface {
  MgConnectivityInfoPlatform() : super(token: _token);

  static final Object _token = Object();

  static MgConnectivityInfoPlatform _instance = MethodChannelMgConnectivityInfo();

  static MgConnectivityInfoPlatform get instance => _instance;

  static set instance(MgConnectivityInfoPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Stream<MGDataConnectionStatus> get dataConnectivityState;

  Stream<MGHotspotStatus> get hotspotStatus;
}
