import { Component } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ArtistsComponent} from '../artists/artists.component';
import {ActivatedRoute} from '@angular/router';
import {ArtistService} from '../../services/artist-service';
import {IArtistRequest, IArtistResponse} from '../../types';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-artist-update-form',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
  ],
  templateUrl: './artist-update-form.component.html',
  styleUrl: './artist-update-form.component.css'
})
export class ArtistUpdateFormComponent {
  artistUpdateForm: FormGroup;
  artistId: string | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private artistService: ArtistService,
    private route: ActivatedRoute,
    private artistComponent: ArtistsComponent
  ) {
    this.artistId = this.route.snapshot.paramMap.get('id');
    const chosenArtist = artistComponent.artists?.find(artist => artist.id === this.artistId);

    this.artistUpdateForm = this.formBuilder.group({
      name: [chosenArtist?.name], //possibly undefined
      surname: [chosenArtist?.surname],
      age: [chosenArtist?.age],
      stagename: [chosenArtist?.stagename],
    });
  }

  onSubmit() {
    this.updateArtist();
  }

  updateArtist() {
    const formData = this.artistUpdateForm.value as IArtistRequest;

    if(!this.artistId) {
      console.log('Invalid artist id');
      return;
    }

    this.artistService.update(this.artistId, formData)
      .subscribe({
        next: (data) => {
          const artistData = data as IArtistResponse;
          this.artistComponent.onArtistUpdated(this.artistId, artistData);
        },
        error: (err) => {
          console.log('Error updating artist', err)
        }
      })
  }
}
