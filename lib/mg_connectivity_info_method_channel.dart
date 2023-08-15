import 'dart:async';

import 'package:flutter/services.dart';
import 'package:mg_connectivity_info/mg_connectivity_info_common.dart';

import 'mg_connectivity_info_platform_interface.dart';

class MethodChannelMgConnectivityInfo extends MgConnectivityInfoPlatform {
  late Stream<MGDataConnectionStatus> _dataConnectionStream;
  late Stream<MGHotspotStatus> _hotspotStatusStream;

  @override
  Stream<MGDataConnectionStatus> get dataConnectivityState {
    // Data connection stream
    const eventChannel = EventChannel("mg/data_connection_state");
    final dataConnectionStreamController =
    StreamController<MGDataConnectionStatus>();
    _dataConnectionStream =
        dataConnectionStreamController.stream.asBroadcastStream();

    eventChannel.receiveBroadcastStream().listen((event) {
      var data = MGDataConnectionStatus.unknown;

      switch (event) {
        case "0":
          data = MGDataConnectionStatus.disconnected;
          break;
        case "1":
          data = MGDataConnectionStatus.gsm;
          break;
        case "2":
          data = MGDataConnectionStatus.edge;
          break;
        case "3":
          data = MGDataConnectionStatus.hsdpa;
          break;
        case "10":
          data = MGDataConnectionStatus.hspa;
          break;
        case "13":
          data = MGDataConnectionStatus.lte;
          break;
        case "20":
          data = MGDataConnectionStatus.nr;
          break;
        default:
          data = MGDataConnectionStatus.unknown;
      }

      dataConnectionStreamController.sink.add(data);
    });

    return _dataConnectionStream;
  }

  @override
  Stream<MGHotspotStatus> get hotspotStatus {
    // Hotspot status stream
    const hotspotEventChannel = EventChannel("mg/hotspot_status_changed");
    final hotspotStatusStreamController = StreamController<MGHotspotStatus>();
    _hotspotStatusStream =
        hotspotStatusStreamController.stream.asBroadcastStream();

    hotspotEventChannel.receiveBroadcastStream().listen((event) {
      if (event) {
        hotspotStatusStreamController.sink.add(MGHotspotStatus.on);
      } else {
        hotspotStatusStreamController.sink.add(MGHotspotStatus.off);
      }
    });

    return _hotspotStatusStream;
  }
}
