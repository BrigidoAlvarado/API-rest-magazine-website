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

export const routes: Routes = [
    //RUTAS GENERALES
    {
        path: 'login',
        title: 'Iniciar Sesion',
        component: LoginComponent
    },
    {
        path: 'newAccount',
        title: 'Crear Cuenta',
        component: NewAccountComponent
    },
    {
        path: 'edit-profile',
        title: 'Editar Perfil',
        component: RedirectToEditProfileComponent
    },
    //RUTAS DEL ADMINISTRADOR
    {
        path: 'admin-home',
        title: 'Home',
        component: AdminHomeComponent
    },
    {
        path: 'admin-profile',
        title: 'Perfil',
        component: AdminProfileComponent
    },{
        path: 'admin-edit-profile',
        title: 'Editar Perfil',
        component: AdminEditProfileComponent
    },
    //RUTAS DEL ANUNCIANTE
    {
        path: 'advertiser-business',
        title: 'Compra y Venta',
        component: AdvertiserBusinessViewComponent
    },
    {
        path: 'advertiser-home',
        title: 'Home',
        component: AdvertiserHomeComponent
    },
    {
        path: 'advertiser-profile',
        title: 'Perfil',
        component: AdvProfileComponent
    },
    {
        path: 'advertiser-edit-profile',
        title: 'Editar Perfil',
        component: AdvertiserEditProfileComponent
    },
    {
        path: 'edit-ad/:id',
        title: 'Editar Perfil',
        component: EditAdViewComponent
    },
    //RUTAS DEL EDITOR
    {
        path: 'editor-home',
        title: 'Home',
        component: EditorHomeComponent
    },
    {
        path: 'editor-profile',
        title: 'home',
        component: EditorProfileComponent
    },
    {
        path: 'editor-edit-profile',
        title: 'Editar Perfil',
        component: EditorEditProfileComponent
    },{
        path: 'editor-post',
        title: 'Publicar Revista',
        component: PostViewComponent
    },
    {
        path: 'editor-business',
        title: 'Tienda',
        component: EditorBusinessViewComponent
    },
    //RUTAS DEL SUSCRIPTOR
    {
        path: 'subscriber-home',
        title: 'home',
        component: SubscriberHomeComponent
    },
    {
        path: 'subscriber-profile',
        title: 'home',
        component: SubsProfileComponent
    },{
        path: 'subscriber-edit-profile',
        title: 'Editar Perfil',
        component: SubscriberEditProfileComponent
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
