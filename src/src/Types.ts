export class JYWhiteBoardInitOptions {
  constructor(public appId: string) {}
}
export class JYWhiteBoardJoinRoomOptions {
  constructor(public roomId: string, public roomToken: string) {}
}

export class JYWhiteBoardRGB {
  constructor(public r: number, public g: number, public b: number) {}
}
export class RoomState {
  constructor(
    public memberState: any,
    public broadcastState: any,
    public zoomScale: number
  ) {}
}
export type Listener = (...args: any[]) => any;
