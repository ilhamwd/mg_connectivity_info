// Codes from native
// 1: GSM, HSCSD, GPRS
// 2: EDGE
// 3: UMTS, HSDPA
// 10: HSPA, HSDPA (SDK >= 31)
// 13: LTE, 4G, 5G (SDK < 31)
// 20: 5G/NR
enum MGDataConnectionStatus { gsm, edge, hsdpa, hspa, lte, nr, disconnected, unknown }

enum MGHotspotStatus {on,off}