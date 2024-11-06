export interface Ad {
    id: number,
    status: boolean,
    cost: number,
    kindAd: string,
    timeAd: string,
    advertiser: string,
    date: Date,
    days: number,
    text: string,
    link: string
    isText: boolean,
    isImage: boolean,
    isVideo: boolean
}