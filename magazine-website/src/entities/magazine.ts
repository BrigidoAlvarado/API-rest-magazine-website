import { Profile } from "./profile";

export interface Magazine {
    tittle: string,
    commentStatus: boolean,
    subscriptionStatus: boolean,
    isLiked: boolean;
    dailyCost: number,
    likes: number,
    id: number,
    category: string,
    description: string,
    editor: string,
    tags:string,
    tagsList: string [],
    date: string,
    file: File,
    subscriberList: Profile[],
    idFilesList: number[],
    comments: string[]
}