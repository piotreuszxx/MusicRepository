import { Routes } from '@angular/router';
import {ArtistUpdateFormComponent} from '../components/artist-update-form/artist-update-form.component';
import {SongUpdateFormComponent} from '../components/song-update-form/song-update-form.component';

export const routes: Routes = [
  { path: 'artist/:id', component: ArtistUpdateFormComponent },
  { path: 'song/:id', component: SongUpdateFormComponent } ,
];

