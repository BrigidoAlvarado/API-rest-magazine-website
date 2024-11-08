import { Ad } from "./ad/ad"
import { AdView } from "./ad/adView"

export interface Advertiser {
    userName: string
    adList: Ad[]
    adViewList: AdView[]
}