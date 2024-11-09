import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { NewAccountComponent } from './user/new-account/new-account.component';
import { LoginComponent } from '../app/user/login/login.component';
import { AdminHomeComponent} from '../app/admin/admin-home/admin-home.component';
import { AdvertiserHomeComponent} from '../app/advertiser/advertiser-home/advertiser-home.component';
import { EditorHomeComponent} from '../app/editor/editor-home/editor-home.component';
import { SubscriberHomeComponent} from '../app/subscriber/subscriber-home/subscriber-home.component';
import { AdminProfileComponent } from '../app/admin/admin-profile/admin-profile.component';
import { AdvProfileComponent} from '../app/advertiser/adv-profile/adv-profile.component'
import { EditorProfileComponent } from '../app/editor/editor-profile/editor-profile.component';
import { SubsProfileComponent } from '../app/subscriber/subs-profile/subs-profile.component';
import { AdminEditProfileComponent } from '../app/admin/admin-edit-profile/admin-edit-profile.component';
import { RedirectToEditProfileComponent } from '../app/user/redirect-to-edit-profile/redirect-to-edit-profile.component'
import { AdvertiserEditProfileComponent } from '../app/advertiser/advertiser-edit-profile/advertiser-edit-profile.component';
import { EditorEditProfileComponent } from '../app/editor/editor-edit-profile/editor-edit-profile.component';
import { SubscriberEditProfileComponent} from '../app/subscriber/subscriber-edit-profile/subscriber-edit-profile.component';
import { AdvertiserBusinessViewComponent } from '../app/advertiser/advertiser-business-view/advertiser-business-view.component';
import { EditorBusinessViewComponent } from './editor/editor-business-view/editor-business-view.component';
import { EditAdViewComponent } from './ad/edit-ad-view/edit-ad-view.component';
import { PostViewComponent } from './editor/post-view/post-view.component';
import { ExplorerComponent } from './subscriber/explorer/explorer.component';
import { MagazineViewComponent } from './subscriber/magazine-view/magazine-view.component';
import { AutorProfileComponent } from './subscriber/autor-profile/autor-profile.component';
import { AdminMagazineViewComponent } from './admin/admin-magazine-view/admin-magazine-view.component';
import { EarningComponent } from './admin/reports/earning/earning.component';
import { AdminAdReportsComponent } from './admin/reports/admin-ad-reports/admin-ad-reports.component';
import { AdminAdvertiserReportComponent } from './admin/reports/admin-advertiser-report/admin-advertiser-report.component';
import { AdminPopularReportComponent } from './admin/reports/admin-popular-report/admin-popular-report.component';
import { IsAdmin } from '../services/validate-role/is-admin';
import { IsEditor } from '../services/validate-role/is-editor';
import { IsAdvertiser } from '../services/validate-role/is-advertiser';
import { IsSubscriber } from '../services/validate-role/is-subscriber';
import { EffectivityAdComponent as AdminEffectivityAdComponent } from './admin/reports/effectivity-ad/effectivity-ad.component';
import { CommentMagazineReportComponent } from './admin/reports/comment-magazine-report/comment-magazine-report.component';
import { EditorPaymentReportComponent } from './editor/reports/editor-payment-report/editor-payment-report.component';
import { EditorFavoriteMagazineReportComponent } from './editor/reports/editor-favorite-magazine-report/editor-favorite-magazine-report.component';
import { EditorSubscriptionReportComponent } from './editor/reports/editor-subscription-report/editor-subscription-report.component';

export const routes: Routes = [
    //RUTAS GENERALES
    {
        path: 'login',
        title: 'Iniciar Sesion',
        component: LoginComponent,
    },
    {
        path: 'newAccount',
        title: 'Crear Cuenta',
        component: NewAccountComponent
    },
    {
        path: 'edit-profile',
        title: 'Editar Perfil',
        component: RedirectToEditProfileComponent,
    },
    //RUTAS DEL ADMINISTRADOR
    {
        path: 'admin-home',
        title: 'Home',
        component: AdminHomeComponent,
        canActivate: [IsAdmin]
    },
    {
        path: 'admin-profile',
        title: 'Perfil',
        component: AdminProfileComponent,
        canActivate: [IsAdmin]
    },{
        path: 'admin-edit-profile',
        title: 'Editar Perfil',
        component: AdminEditProfileComponent,
        canActivate: [IsAdmin]
    },
    {
        path: 'admin-magazine',
        title: 'Revistas',
        component: AdminMagazineViewComponent,
        canActivate: [IsAdmin]
    },
    {
        path: 'admin-earning',
        title: 'Reportes de Ganancias',
        component: EarningComponent,
        canActivate: [IsAdmin]
    },
    {
        path: 'admin-ad-report',
        title: 'Reporte de Anuncios',
        component: AdminAdReportsComponent,
        canActivate: [IsAdmin]
    },
    {
        path: 'admin-advertiser-report',
        title: 'Reporte de Ganancias Anunciante',
        component: AdminAdvertiserReportComponent,
        canActivate: [IsAdmin]
    },
    {
        path: 'admin-popular-report',
        title: 'Reporte Revistas Populares',
        component: AdminPopularReportComponent,
        canActivate: [IsAdmin]
    },
    {
        path: 'app-effectivity-ad',
        title: 'Reporte Efectividad Anucios',
        component: AdminEffectivityAdComponent,
        canActivate: [IsAdmin]
    },
    {
        path: 'comment-magazine-report',
        title: 'Reporte Revistas Comentadas',
        component: CommentMagazineReportComponent,
        canActivate: [IsAdmin]
    },
    //RUTAS DEL ANUNCIANTE
    {
        path: 'advertiser-business',
        title: 'Tienda',
        component: AdvertiserBusinessViewComponent,
        canActivate: [IsAdvertiser]
    },
    {
        path: 'advertiser-home',
        title: 'Home',
        component: AdvertiserHomeComponent,
        canActivate: [IsAdvertiser]
    },
    {
        path: 'advertiser-profile',
        title: 'Perfil',
        component: AdvProfileComponent,
        canActivate: [IsAdvertiser]
    },
    {
        path: 'advertiser-edit-profile',
        title: 'Editar Perfil',
        component: AdvertiserEditProfileComponent,
        canActivate: [IsAdvertiser]
    },
    {
        path: 'edit-ad/:id',
        title: 'Editar Anuncio',
        component: EditAdViewComponent,
        canActivate: [IsAdvertiser]
    },
    //RUTAS DEL EDITOR
    {
        path: 'editor-home',
        title: 'Home',
        component: EditorHomeComponent,
        canActivate: [IsEditor]
    },
    {
        path: 'editor-profile',
        title: 'home',
        component: EditorProfileComponent,
        canActivate: [IsEditor]
    },
    {
        path: 'editor-edit-profile',
        title: 'Editar Perfil',
        component: EditorEditProfileComponent,
        canActivate: [IsEditor]
    },{
        path: 'editor-post',
        title: 'Publicar Revista',
        component: PostViewComponent,
        canActivate: [IsEditor]
    },
    {
        path: 'editor-business',
        title: 'Tienda',
        component: EditorBusinessViewComponent,
        canActivate: [IsEditor]
    },
    {
        path: 'editor-payment-report',
        title: 'Reporte de Pagos',
        component: EditorPaymentReportComponent,
        canActivate: [IsEditor]
    },
    {
        path: 'editor-favorite-magazine-report',
        title: 'Reporte de Revistas Favoritas',
        component: EditorFavoriteMagazineReportComponent,
        canActivate: [IsEditor]
    },
    {
        path: 'editor-subscription-report',
        title: 'Reporte de Suscripcines',
        component: EditorSubscriptionReportComponent,
        canActivate: [IsEditor]
    },
    //RUTAS DEL SUSCRIPTOR
    {
        path: 'subscriber-home',
        title: 'home',
        component: SubscriberHomeComponent,
        canActivate: [IsSubscriber]
    },
    {
        path: 'subscriber-profile',
        title: 'home',
        component: SubsProfileComponent,
        canActivate: [IsSubscriber]
    },{
        path: 'subscriber-edit-profile',
        title: 'Editar Perfil',
        component: SubscriberEditProfileComponent,
        canActivate: [IsSubscriber]
    },{
        path: 'subscriber-explorer',
        title: 'Buscar Revistas',
        component: ExplorerComponent,
        canActivate: [IsSubscriber]
    },{
        path: 'magazine/:id',
        title: 'Revista',
        component: MagazineViewComponent,
        canActivate: [IsSubscriber]
    },{
        path: 'autor-profile/:userName',
        title: 'Perfil de Autor',
        component: AutorProfileComponent,
        canActivate: [IsSubscriber]
    },
    //EXTRAS
    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'
    },
    {
        path: '**',
        redirectTo: 'login'
    }  
];