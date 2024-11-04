import { Ad } from "../ad/ad";
import { LockAd } from "../lockAd";
import { Magazine } from "../magazine";

export interface EarningReport {
    magazineList: Magazine[],
    adList: Ad[],
    lockAdList: LockAd[],

    totalIncome: number;
    totalExpenses: number;
    totalProfits: number;
}