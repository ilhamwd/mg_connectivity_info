
import 'package:mg_connectivity_info/mg_connectivity_info_common.dart';

import 'mg_connectivity_info_platform_interface.dart';

import 'mg_connectivity_info_common.dart';

class MgConnectivityInfo {
  Stream<MGDataConnectionStatus> get dataConnectivityState {
    return MgConnectivityInfoPlatform.instance.dataConnectivityState;
  }

  Stream<MGHotspotStatus> get hotspotStatus {
    return MgConnectivityInfoPlatform.instance.hotspotStatus;
  }
}
