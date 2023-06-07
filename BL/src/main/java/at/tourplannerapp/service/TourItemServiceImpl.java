package at.tourplannerapp.service;


import at.tourplannerapp.Entities.TourItemEntity;
import at.tourplannerapp.Repositories.TourItemRepository;
import at.tourplannerapp.model.TourItem;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class TourItemServiceImpl implements TourItemService{

    private static final Logger logger = LogManager.getLogger(TourItemServiceImpl.class);

    private final TourItemRepository tourItemRepository;

    public TourItemServiceImpl(TourItemRepository tourItemRepository) {
        this.tourItemRepository = tourItemRepository;
    }

    @Override
    public List<TourItem> getAll() {
        List<TourItemEntity> tourItemEntities = tourItemRepository.findAll();
        return tourItemEntities.stream().map(tourItemEntity ->
                new TourItem(tourItemEntity.getTourId(), tourItemEntity.getName(), tourItemEntity.getDescription(), tourItemEntity.getFromLocation(),
                        tourItemEntity.getToLocation(), tourItemEntity.getTransportationType(), tourItemEntity.getDistance(),
                        tourItemEntity.getEstimatedTime(), tourItemEntity.getPathToMap(), tourItemEntity.getUsername())).toList();
    }

    @Override
    public TourItem create() {
        TourItemEntity tourItemEntity = new TourItemEntity();
        tourItemRepository.save(tourItemEntity);
        tourItemEntity.setName("Tour " + tourItemEntity.getTourId());
        return new TourItem(tourItemEntity.getTourId(), tourItemEntity.getName(), tourItemEntity.getDescription(), tourItemEntity.getFromLocation(),
                tourItemEntity.getToLocation(), tourItemEntity.getTransportationType(), tourItemEntity.getDistance(),
                tourItemEntity.getEstimatedTime(), tourItemEntity.getPathToMap(), tourItemEntity.getUsername());
    }


    @Override
    public void delete(TourItem TourItem) {

    }

    /*


    @Override
    public List<MediaItem> getAll() {
        List<MediaItemEntity> mediaItemEntities = mediaItemRepository.findAll();
        return mediaItemEntities.stream().map(mediaItemEntity ->
                new MediaItem(mediaItemEntity.getId(), mediaItemEntity.getName(), mediaItemEntity.getDuration(), mediaItemEntity.getContent())).toList();
    }

    @Override
    public MediaItem create() {
        MediaItemEntity mediaItemEntity = new MediaItemEntity();
        mediaItemRepository.save(mediaItemEntity);
        mediaItemEntity.setName("Tour " + mediaItemEntity.getId());
        return new MediaItem(mediaItemEntity.getId(), mediaItemEntity.getName(), mediaItemEntity.getDuration(), mediaItemEntity.getContent());
    }

    @Override
    public void delete(MediaItem mediaItem) {

    }
    */

}